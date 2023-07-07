import os
from typing import SupportsRound
import boto3
import datetime
from dotenv import load_dotenv

load_dotenv(verbose=True)


def aws_session(region_name='ap-northeast-2'):
    return boto3.session.Session(
        aws_access_key_id=os.getenv('AWS_ACCESS_KEY_ID'),
        aws_secret_access_key=os.getenv('AWS_ACCESS_KEY_SECRET'),
        region_name=region_name)


def upload(bucket_name, file_path):
    session = aws_session()
    s3_resource = session.resource('s3')  # s3에 대한 권한 및 상태를 s3(변수)에 저장

    file_dir, file_name = os.path.split(file_path)
    bucket = s3_resource.Bucket(bucket_name)
    bucket.upload_file(Filename=file_path,
                       Key=file_name,
                       ExtraArgs={'ACL': 'public-read'})

    s3_url = f"https://{bucket_name}.s3.amazonaws.com/{file_name}"
    return s3_url


if __name__ == "__main__":
    aws_session()

    now = datetime.datetime.now()
    now_date = now.strftime('%Y%m%d')
    file_name = now_date + ".csv"
    file_path = f"data/{file_name}"

    s3_url = upload('s3-loobie', file_path)
    print(s3_url)