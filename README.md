- Execute este comando via docker para criar um container de mysql:
1 - docker run -p 30000:3306 -d -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=contrus mysql:8.2

2 - Use o vscode para executar o projeto.

3 - Baixe a JDK 21. Link: https://adoptium.net/temurin/releases/?os=windows&arch=x64&package=jdk

4 - Na pasta ".vscode" e no arquivo settings.json, voce vai ter os caminhos do java que foram referenciados, pelos plugins do vscode. Aponte para a mesma versão do java que você baixou. Fazendo isso, não vai ser necessário inserir no "JAVA_HOME" o path do JDK 21.

5 - Com o vscode aberto, pressione o F1 e escolha a opção "Run Task". Logo após escolha mvnw "clean-compile-install".

6 - No ícone "Run and Debug", no vscode, escolha a opção "WebApi - Production".

7 - =D