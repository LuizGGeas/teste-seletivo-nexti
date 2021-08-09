# teste-seletivo-nexti
Teste seletivo para a empresa Nexti

Para executar o banco de dados do projeto, é necessário a instalação do Docker.
Após instalado, rodar o seguinte comando `docker run --name postgres -e POSTGRES_PASSWORD=postgres -p 32768:5432 -d postgres:12`;
Então é necessário rodar os seguintes comandos dentro do banco de dados:
<ul>
  <li>`CREATE ROLE nexti WITH 	SUPERUSER	CREATEDB	CREATEROLE	INHERIT	LOGIN	REPLICATION	BYPASSRLS CONNECTION LIMIT UNLIMITED PASSWORD 'nexti'`</li>
  <li>`CREATE DATABASE nexti with owner nexti`</li>
</ul>
Após executado a criação do banco com o usuário, apenas é necessário rodar 
