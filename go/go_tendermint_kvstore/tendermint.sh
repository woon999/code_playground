#! /bin/sh

rm -rf /tmp/example
TMHOME="/tmp/example" tendermint init

CONFIG_FILE="/tmp/example/config/config.toml"
MAX_TX_BYTES=$(awk -F' = ' '/^max_tx_bytes/ {print $2}' "$CONFIG_FILE")

NEW_MAX_BATCH_BYTES=$((MAX_TX_BYTES + 1))
sed -i'' -e "s/^max_batch_bytes = .*/max_batch_bytes = $NEW_MAX_BATCH_BYTES/" "$CONFIG_FILE"

./example -config "/tmp/example/config/config.toml"