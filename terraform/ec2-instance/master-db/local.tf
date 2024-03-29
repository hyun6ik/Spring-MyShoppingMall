locals {
  region = var.region

  ec2_name = format("%s-ec2", var.name)
  ssh_sg_name = format("%s-ssh-sg", var.name)
  mysql_sg_name = format("%s-mysql-sg", var.name)

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

  mysql_sg_description = var.mysql_sg_description
  mysql_ingress_cidr_blocks = var.mysql_ingress_cidr_blocks
  mysql_ingress_rules = var.mysql_ingress_rules
  mysql_egress_rules = var.mysql_egress_rules

  mysql_tcp_listeners = var.mysql_tcp_listeners
  mysql_tcp_listener_rules = var.mysql_tcp_listener_rules
}
