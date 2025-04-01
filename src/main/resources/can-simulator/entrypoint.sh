#!/bin/bash

# Ativa a interface CAN virtual
modprobe vcan
ip link add dev vcan0 type vcan
ip link set up vcan0

echo "Interface CAN vcan0 configurada."

# Loop para enviar mensagens CAN simuladas
while true; do
  cansend vcan0 123#DEADBEEF
  echo "Mensagem CAN enviada: 123#DEADBEEF"
  sleep 1
done

