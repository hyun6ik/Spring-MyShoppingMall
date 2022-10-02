variable "env" {}
variable "name" {}
variable "owner" {}

variable "ami_owners" {}
variable "ami_filters" {}
variable "instance_type" {}
variable "key_name" {}

variable "ssh_sg_description" {}
variable "ssh_ingress_cidr_blocks" {}
variable "ssh_ingress_rules" {}
variable "ssh_egress_rules" {}

variable "mysql_sg_description" {}
variable "mysql_ingress_cidr_blocks" {}
variable "mysql_ingress_rules" {}
variable "mysql_egress_rules" {}
variable "mysql_tcp_listeners" {}
variable "mysql_tcp_listener_rules" {}
