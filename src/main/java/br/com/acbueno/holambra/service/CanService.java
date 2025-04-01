package br.com.acbueno.holambra.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.acbueno.holambra.model.CanMessage;
import br.com.acbueno.holambra.repository.CanRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CanService {

  @Autowired
  CanRepository repository;

  @PostConstruct
  public void init() {
    log.info("Iniciando CanService...");
    startListening();
  }

  private void startListening() {
    log.info("Tentando iniciar captura de mensagens CAN...");

    new Thread(() -> {
      try {
        log.info("Verificando se 'candump' está disponível...");
        Process testProcess = new ProcessBuilder("which", "candump").start();
        BufferedReader testReader =
            new BufferedReader(new InputStreamReader(testProcess.getInputStream()));
        String candumpPath = testReader.readLine();

        if (candumpPath == null || candumpPath.isEmpty()) {
          log.error(
              "O comando 'candump' não foi encontrado! Verifique se a ferramenta está instalada.");
          return;
        }
        log.info("'candump' encontrado em: {}", candumpPath);

        log.info("Iniciando leitura de mensagens CAN com candump...");
        ProcessBuilder pb = new ProcessBuilder("candump", "vcan0");
        pb.redirectErrorStream(true);
        Process process = pb.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;

        while ((line = reader.readLine()) != null) {
          log.info("Mensagem recebida do CAN: {}", line);
          CanMessage message = new CanMessage();
          message.setMessage(line);
          message.setTimestamp(String.valueOf(System.currentTimeMillis()));
          repository.save(message);
          log.info("Mensagem salva no MongoDB: {}", line);
        }
      } catch (Exception e) {
        log.error("Erro ao iniciar leitura CAN: {}", e.getMessage(), e);
      }
    }).start();

    log.info("Thread de captura de mensagens CAN iniciada com sucesso!");
  }

  public List<CanMessage> findAllMessage() {
    return repository.findAll();
  }
}
