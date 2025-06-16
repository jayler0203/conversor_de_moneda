# Conversor de Moneda

Este es un proyecto en Java que permite convertir entre diferentes monedas utilizando tasas de cambio obtenidas desde una API externa.

## Características

- Conversión entre ARS (Peso Argentino), USD (Dólar Estadounidense), COP (Peso Colombiano) y BRL (Real Brasileño).
- Interfaz de línea de comandos interactiva.
- Obtención automática de tasas de cambio actualizadas.

## Requisitos

- Java 17 o superior.
- Dependencia de [Gson](https://github.com/google/gson) para el manejo de JSON.
- Acceso a internet para consultar la API de tasas de cambio.

## Instalación

1. Clona este repositorio o descarga el código fuente.
2. Asegúrate de tener las dependencias necesarias (especialmente Gson).

   - **Si usas IntelliJ IDEA:**  
     Ve a `File > Project Structure > Libraries`, haz clic en el botón `+`, selecciona `Java` y agrega el archivo `gson-2.10.1.jar` que se encuentra en el directorio `lib` del proyecto.

   - **Recomendado: usa un gestor de dependencias como Maven o Gradle:**  
     Esto facilita la gestión de librerías y dependencias.  
     - Para **Maven**, agrega en tu `pom.xml`:
       ```xml
       <dependency>
         <groupId>com.google.code.gson</groupId>
         <artifactId>gson</artifactId>
         <version>2.10.1</version>
       </dependency>
       ```
     - Para **Gradle**, agrega en tu `build.gradle`:
       ```groovy
       implementation 'com.google.code.gson:gson:2.10.1'
       ```

3. Compila y ejecuta el proyecto desde tu IDE o usando los comandos de Maven/Gradle según corresponda.

## Uso

Al ejecutar el programa, se mostrará un menú con las opciones de conversión. Selecciona la opción deseada e ingresa la cantidad a convertir.

## Créditos

- Proyecto realizado como parte de los cursos de Alura Latam.
- API utilizada: [ExchangeRate-API](https://www.exchangerate-api.com/)


