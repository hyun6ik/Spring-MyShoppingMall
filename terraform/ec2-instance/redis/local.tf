locals {
  region = var.region

  ec2_name = format("%s-ec2", var.name)
  ssh_sg_name = format("%s-ssh-sg", var.name)
  redis_sg_name = format("%s-redis-sg", var.name)
  role_name    = format("%s-role", var.name)


  tags = merge(var.tags, { Owner = var.owner, Environment = var.env })

  vpc_id            = data.terraform_remote_state.vpc.outputs.vpc_id
  public_subnet_ids = data.terraform_remote_state.vpc.outputs.public_subnet_ids
  azs               = data.terraform_remote_state.vpc.outputs.azs
  default_sg_id     = data.terraform_remote_state.vpc.outputs.default_security_group_id

  ami_id        = data.aws_ami.this.id
  ami_owners    = var.ami_owners
  ami_filters   = var.ami_filters
  instance_type = var.instance_type
  key_name      = var.key_name

  ssh_sg_description      = var.ssh_sg_description
  ssh_ingress_cidr_blocks = var.ssh_ingress_cidr_blocks
  ssh_ingress_rules       = var.ssh_ingress_rules
  ssh_egress_rules        = var.ssh_egress_rules

  redis_sg_description = var.redis_sg_description
  redis_ingress_cidr_blocks = var.redis_ingress_cidr_blocks
  redis_ingress_rules = var.redis_ingress_rules
  redis_egress_rules = var.redis_egress_rules

  redis_tcp_listeners = var.redis_tcp_listeners
  redis_tcp_listener_rules = var.redis_tcp_listener_rules

}
