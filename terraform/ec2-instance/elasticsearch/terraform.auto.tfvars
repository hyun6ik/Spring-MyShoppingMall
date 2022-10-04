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
instance_type = "t2.micro"
key_name      = "dev"

# ssh sg
ssh_sg_description      = "SSH Security group for Bastion EC2 instance"
ssh_ingress_cidr_blocks = ["0.0.0.0/0"]
ssh_ingress_rules       = ["ssh-tcp"]
ssh_egress_rules        = ["all-all"]

elasticsearch_sg_description = "ElasticSearch Security group for Bastion EC2 instance"
elasticsearch_ingress_cidr_blocks = ["0.0.0.0/0"]
elasticsearch_ingress_rules = ["elasticsearch-tcp"]
elasticsearch_egress_rules = ["all-all"]

elasticsearch_tcp_listeners = [
  {
    port = 9200
    protocol = "TCP"
    action_type = "fixed-response"
    fixed_response = {
      content_type = "text/plain"
      message_body = "Not Found"
      status_code = "403"
    }
  }
]

elasticsearch_tcp_listener_rules = [
  {
    mysql_listener_index = 0
    actions = [{
      type = "forward"
      target_group_index = 0
    }]
    conditions = [{
      path_patterns = ["/*"]
    }]
  }
]
