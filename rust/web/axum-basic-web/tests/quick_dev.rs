#![allow(unused)] // For beginning only.

use anyhow::Result;
use serde_json::json;

#[tokio::test]
async fn quick_dev() -> Result<()> {
    let hc = httpc_test::new_client("https://localhost:8080")?;

    hc.do_get("/health").await?.print().await?;


    Ok(())
}