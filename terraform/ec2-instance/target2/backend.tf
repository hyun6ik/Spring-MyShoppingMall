terraform {
  backend "s3" {
    bucket = "hyun6ik-shoppingmall-backend"
    key = "ec2/target2/terraform.tfstate"
    region = "ap-northeast-2"
    max_retries = 3
  }
}
