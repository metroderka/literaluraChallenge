# Literalura Challenge

### _Catálogo de Libros con interacción textual_

## Objetivo:  
 Realizar solicitudes a una API de libros, a manipular datos JSON, guardarlos en una base de datos y, finalmente, a filtrar y mostrar los libros y autores de interés.
 
## Entorno de desarrollo utilizado:

* Java (versión 17 en adelante)
* Maven: versión 4 en adelante
* Spring: versión 3.3.5
* Postgres: versión 16 en adelante - 
* IDE IntelliJ IDEA -
 
__Configuración al crear el proyecto en Spring Initializr:__

* Java (versión 17 en adelante)
* Maven (Initializr utiliza la versión 4)
* Spring Boot (versión 3.2.3)
* Proyecto en JAR
    
__Dependencias para agregar al crear el proyecto en Spring Initializr:__
* Spring Data JPA
* Postgres Driver    

## Interacción con el usuario:
* Menú con 5 opciones :
  1. Buscar libro por título 
     * Busca en gtuendex.com y guarda en base relacional Postgresql 
  2. Listar libros registrados
  3. Listar autores registrados
  4. Listar autores vivos en un determinado año
  5. Listar libros por idioma  

## Aspectos del proyecto:

* Se deciddó por el uso de una sola Entidad/Modelo y su consiguiente tabla en la base de datos Postgres. 
* Se utilizaron variables de entorno para el acceso a base de datos
* Se utiliza Optional para reemplazar los null por NA en autores desconocidos y por -1 en años de nacimiento y deceso faltantes.
