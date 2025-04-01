FROM ubuntu:latest

# Atualiza pacotes e instala ferramentas necessárias
RUN apt update && apt install -y iproute2 can-utils kmod

# Copia o script de inicialização para o contêiner
COPY entrypoint.sh /entrypoint.sh
RUN chmod +x /entrypoint.sh

# Define o script de entrada
ENTRYPOINT ["/entrypoint.sh"]

