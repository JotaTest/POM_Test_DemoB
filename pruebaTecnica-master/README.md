# Automatización de Pruebas - AutomatizacionPragma

Este repositorio contiene la implementación de pruebas automatizadas utilizando Selenium WebDriver para el sitio web DemoBlaze.

## Ejercicio Propuesto

1. Automatice los casos de prueba necesarios para realizar adiciones al carrito en la página DemoBlaze.
2. Utilice Selenium WebDriver y las librerías externas que considere necesarias (por ejemplo, Apache POI, TestNG, JUnit, etc.).
3. Siga el patrón de diseño de Page Object Model (P.O.M) o Screenplay.
4. Mantenga los principios de la metodología de desarrollo BDD.
5. Asegúrese de que las pruebas sean ejecutables en al menos 2 navegadores (por ejemplo, Chrome, Safari, Mozilla Firefox, Opera, etc.).
6. Organice el código para facilitar su lectura e implemente buenas prácticas de Programación Orientada a Objetos (P.O.O.).
7. Genere reportes que evidencien la correcta ejecución de las pruebas.

## Requerimientos

- Java (JDK 8 o superior)
- Maven
- Selenium WebDriver
- TestNG

## Instalación

1. Descomprime el archivo en tu máquina local
2. Instala dependencias
``` bash
mvn clean install
``` 

## Pruebas
Ejecuta las pruebas con Maven:
``` bash
mvn test
``` 

## Reportes

Los reportes generados se encuentran en la carpeta [target/surefire-reports/html/index.html](target\surefire-reports\html\index.html).

