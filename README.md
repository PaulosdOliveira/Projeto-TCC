![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
# Safira (Plataforma de Recrutamento - Backend)

## Descrição
Backend da plataforma de recrutamento, desenvolvido em Java com Spring Boot, que permite candidatos criarem perfis completos e empresas buscarem candidatos por qualificações. Inclui funcionalidades de gestão de vagas, chat em tempo real via WebSocket e autenticação com JWT. Integrado a um frontend em React/Next.js.

## Tecnologias
- Java 23 
- Spring Boot  
- Spring Data JPA / Hibernate  
- MySQL  
- Maven  
- WebSocket (chat em tempo real)
- JJWT 

## Funcionalidades
- CRUD de candidatos  
- Gestão de qualificações, experiências, cursos e formações  
- Publicação e busca de vagas  
- Chat em tempo real entre candidatos e empresas  
- Autenticação e autorização via token JWT

## Como rodar
 1. Instalar o JDK 23
 - Link: <a href="https://www.oracle.com/java/technologies/javase/jdk23-archive-downloads.html" target="_blank">DownLoad JDK 23</a>
 2. Instalar o Intellij Community
 - Link: <a href="https://www.jetbrains.com/idea/download/download-thanks.html?platform=windows&code=IIC" target="_blank">Download Intellij</a>
 3. Instalar o "MySQL WorkBench" e o "XAMPP"
 -  Link: <a href="https://dev.mysql.com/downloads/workbench/" target="_blank">DownLoad MySQL WorkBench</a>
 -  Link: <a href="https://www.apachefriends.org/pt_br/download.html" target="_blank">DownLoad XAMPP</a>
 4. Criar o banco de dados com o script localizado no repositório em "/safira/src/main/resources/scriptSql.txt"
 - Link: <a href="https://github.com/PaulosdOliveira/Safira-Back-End/blob/main/selectAspi/src/main/resources/scriptSql.txt">Script SQL</a>

 ### POR ULTIMO É SÓ RODAR O BANCO DE DADOS E O BACK END EM SEGUIDA

