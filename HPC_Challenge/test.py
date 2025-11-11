import re
import pandas as pd
from datetime import datetime
import glob
import os

# === 1. Parse Serio 4000 log files ===
def parse_serio_log(path):
    """
    Parse a Serio 4000 log file.
    Extract timestamp, parameter name, and value.
    Handles both numeric and string values.
    """
    # Regex pattern for log lines
    pattern = re.compile(
        r'(?P<ts>\d{2}-\d{2}-\d{4} \d{2}:\d{2}:\d{2}).*?CONTROL name="(?P<name>[^"]+)" value="(?P<value>[^"|]+)'
    )
    rows = []
    # Open file with latin1 encoding to avoid Unicode errors
    with open(path, encoding='latin1') as f:
        for line in f:
            m = pattern.search(line)
            if not m:
                continue
            # Parse timestamp
            ts = datetime.strptime(m.group('ts'), "%d-%m-%Y %H:%M:%S")
            val_str = m.group('value')
            # Try to convert value to float, otherwise leave as string
            try:
                val = float(val_str)
            except ValueError:
                val = val_str
            rows.append({
                "timestamp": ts,
                "machine": "Serio4000",
                "parameter": m.group('name').replace('.value', ''),
                "value": val
            })
    return pd.DataFrame(rows)

# === 2. Parse Pi-Pico-M CSV files ===
def parse_pipicom(path):
    """
    Parse Pi-Pico-M CSV file.
    Detects separator automatically, normalizes timestamps,
    and melts all columns into parameter/value pairs.
    """
    df = pd.read_csv(path, sep=None, engine='python')

    # Convert timestamp column to datetime
    def fix_ts(x):
        for fmt in ("%d.%m.%Y %H:%M", "%d.%m.%Y %H:%M:%S"):
            try:
                return datetime.strptime(str(x), fmt)
            except Exception:
                pass
        return pd.NaT

    df['timestamp'] = df.iloc[:,0].apply(fix_ts)
    df['machine'] = 'PiPicoM'

    # Melt all columns except timestamp and machine
    id_cols = ['timestamp', 'machine']
    df_melted = df.melt(id_vars=id_cols, var_name='parameter', value_name='value')

    # Convert numeric values to float if possible
    def try_float(x):
        try:
            return float(x)
        except ValueError:
            return x

    df_melted['value'] = df_melted['value'].apply(try_float)
    return df_melted.dropna(subset=['value'])

# === 3. Merge both DataFrames ===
def merge_all(serio_df, pipico_df):
    """
    Concatenate Serio 4000 and Pi-Pico-M data,
    sort by timestamp, and reset index.
    """
    merged = pd.concat([serio_df, pipico_df]).sort_values('timestamp').reset_index(drop=True)
    return merged

# === 4. Main execution ===
if __name__ == "__main__":
    BASE_DIR = os.path.dirname(__file__)  # directory of this script
    os.chdir(BASE_DIR)  # set working directory to script folder

    # --- Serio 4000 logs ---
    serio_files = glob.glob("*.dtklog")
    serio_list = [parse_serio_log(f) for f in serio_files] if serio_files else []
    serio = pd.concat(serio_list, ignore_index=True) if serio_list else pd.DataFrame()
    print(f"Found {len(serio_files)} Serio 4000 log files.")

    # --- Pi-Pico-M CSVs ---
    pipico_files = glob.glob("*.csv")
    pipico_list = [parse_pipicom(f) for f in pipico_files] if pipico_files else []
    pipico = pd.concat(pipico_list, ignore_index=True) if pipico_list else pd.DataFrame()
    print(f"Found {len(pipico_files)} Pi-Pico-M CSV files.")

    # --- Merge and save ---
    if serio.empty and pipico.empty:
        print("❌ No data to merge.")
    else:
        merged = merge_all(serio, pipico)
        merged.to_csv("merged_data.csv", index=False)
        print("✅ Merged data saved to merged_data.csv")
        print(merged.head())
