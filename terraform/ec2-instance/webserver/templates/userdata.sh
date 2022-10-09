#!/bin/bash

## Nginx
sudo apt update

sudo apt install nginx -y

sudo service start nginx

## Certbot
sudo snap install core
sudo snap install --classic certbot

sudo ln -s /snap/bin/certbot /usr/bin/certbot
