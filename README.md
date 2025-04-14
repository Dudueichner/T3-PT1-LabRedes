# T3 - Parte I - Laboratório de Redes de Computadores

Este trabalho implementa uma aplicação Cliente/Servidor para transferência de arquivos `.txt` usando **sockets** em **Java**, com duas versões distintas:

Autor: Eduardo Felber Eichner

---

## Pré-requisitos

- Linux
- Java 8 ou superior instalado
- Wireshark (para análise de pacotes)
- `tc` (`iproute2`) instalado:

---

## Etapa 1 – Testes normais com TCP e UDP

### 1. Compile os arquivos

```bash
javac *.java
```

### 2. Em dois terminais separados:

#### TCP

**Terminal 1 – Servidor TCP**

```bash
java ServidorTCP
```

**Terminal 2 – Cliente TCP**

```bash
java ClienteTCP
```

#### UDP

**Terminal 1 – Servidor UDP**

```bash
java ServidorUDP
```

**Terminal 2 – Cliente UDP**

```bash
java ClienteUDP
```


---

## Etapa 2 – Análise com Wireshark

### Passos:

1. Inicie o Wireshark
2. Selecione a interface `lo` (loopback)
3. Aplique filtros conforme o teste:

- TCP: `tcp.port == 12345`
- UDP: `udp.port == 12346`

4. Execute cliente e servidor
5. Analise os pacotes transmitidos

---

## Etapa 3 – Simulação de Perda de Pacotes

### Adicionar perda (ex: 30%):

```bash
sudo tc qdisc add dev lo root netem loss 30%
```

### Executar os testes normalmente (cliente e servidor)

### Remover após o teste:

```bash
sudo tc qdisc del dev lo root
```

---

## Etapa 4 – Simulação de Latência Variável

### Adicionar atraso:

```bash
sudo tc qdisc add dev lo root netem delay 100ms 20ms distribution normal
```

Este comando adiciona um atraso de 100ms com variação de ±20ms

### Executar os testes normalmente (cliente e servidor)

### Remover após o teste:

```bash
sudo tc qdisc del dev lo root
```

