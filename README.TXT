Sistema de Cajero Electronico del Banco un Banco
(C) Guillermo Jimenez 2014.

Este ejemplo muestra la implementacion parcial de un sistema de cajero
electronico en el servidor de aplicaciones Tomcat, accediendo a una base de
datos en MySQL. (Los comandos siguientes son para Windows.)

Para ponerlo en operaci�n se requieren varios pasos.
0. Se asume que se tiene instalada una versi�n reciente de Java.
1.  Descargar e instalar MySQL
2.  Crear la base de datos CAJERO con la tabla 'cuenta', conteniendo los campos
       (se puede utilizar una herramienta como navicat (www.navicat.com:) 
      -ncuenta: integer (key)
      -nombre: string (longitud 30)
      -saldo: float (2 decimales)
3.  Agregar por lo menos un registro a la tabla de cuentas
4.  Instalar Tomcat.
5.  Intalar mysql-connector-java-5.1.6-bin.jar dentro de C:\...Tomcat\lib
6.  Agregar a CLASSPATH el archivo C:\...Tomcat\lib\mysql-connector-java-5.1.6-bin.jar
7.  Agregar a CLASSPATH el archivo C:\...Tomcat\lib\servlet-api.jar
8.  Desempacar el archivo 'cajero.zip' dentro del directorio Tomcat\webapps
--  Agregar a CLASSPATH el directorio C:\...Tomcat\webapps\Cajero\WEB-INF\classes
9.  Compilar utilizando el comando (en una ventana de Command): 
      C:\..\webapps\Cajero\Entidades> javac -d ..\WEB-INF\classes *.java
      C:\..\webapps\Cajero\Controles> javac -d ..\WEB-INF\classes *.java
      C:\..\webapps\Cajero\Interfaces> javac -d ..\WEB-INF\classes *.java
10. Iniciar la ejecucion de Tomcat (C:\...Tomcat\bin\startup.bat).
11. Indicar en el navegador Web el URL:
    http://localhost:8080/Cajero/

NOTAS ADICIONALES:
-El archivo de configuracion 'web.xml' especifica los Servlets que seran desplegados
-Al modificar CLASSPATH asegurarse de dar toda la trayectoria desde C:\...
-El c�digo en Entidades asume que en MySQL: user=root y password=root 
