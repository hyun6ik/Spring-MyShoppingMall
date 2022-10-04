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

variable "elasticsearch_sg_description" {}
variable "elasticsearch_ingress_cidr_blocks" {}
variable "elasticsearch_ingress_rules" {}
variable "elasticsearch_egress_rules" {}
variable "elasticsearch_tcp_listeners" {}
variable "elasticsearch_tcp_listener_rules" {}
