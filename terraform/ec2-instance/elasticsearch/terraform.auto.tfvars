env   = "dev"
name  = "slave-db"
owner = "hyun6ik"
tags  = {}

# AMI
ami_owners = ["099720109477"]
ami_filters = [
  {
    name   = "name"
    values = ["ubuntu/images/hvm-ssd/ubuntu-focal-20.04-amd64-server-*"]
  },
  {
    name   = "virtualization-type"
    values = ["hvm"]
  }
]

# EC2
instance_type = "t3.small"
key_name      = "dev"

# ssh sg
ssh_sg_description      = "SSH Security group for Bastion EC2 instance"
ssh_ingress_cidr_blocks = ["0.0.0.0/0"]
ssh_ingress_rules       = ["ssh-tcp"]
ssh_egress_rules        = ["all-all"]
