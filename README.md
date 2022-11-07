
# Prueba apiux

Descripción:
Este proyecto corresponde a la prueba tecnica solicitada por apiux, la cual consta de un CRUD simple construido bajo sping boot.

## Deployment

Para el levantamiento del proyecto es necesario instalar el motor de base de datos postgresql 15, desde el sitio web [oficial](https://www.postgresql.org/download/)

Una vez instalado el motor es necesario crear una nueva base de datos llamada "apiux-prueba"

Cabe mencionar que es necesario habilitar la conexión en modo local para que así el sistema pueda conectarse a la base datos java, el siguiente [enlace](https://stackoverflow.com/questions/3278379/how-to-configure-postgresql-to-accept-all-incoming-connections) puede ayudar a eso, es importante señalar que este sistema es solo a modo de pruebas y jamás debe ser usado como desarrollo final

Realizado los pasos anteriores solo basta con realizar los cambios en el archivo aplication.properties de la siguiente manera.

```
spring.datasource.url=jdbc:postgresql://"ruta accesso a base de datos + nombre BD"
spring.datasource.username="usuario con el cual fue creado la BD"
spring.datasource.password="contraseña de usuario"
```



## Demo

Para realizar una deemo del sistema es necesario tener postman instalado, el siguiente [enlace](https://www.postman.com/downloads/)
puede facilitar la descarga de este programa

Una vez instalado es necesario configurar la autenticación para esto se debe agregar una configuracion de tipo "Basic Auth" realizar las peticiones donde el usuario a utilizar es user y la clave password, tras esto es necesario configurar la peticion.

Una vez levantado el proyecto para acceder al detalle del API debe ingresar al siguiente ruta http://localhost:8080/swagger-ui/index.html

En este lugar podrá ver el detalle de las rutas disponibles de dicho proyecto.
## Authors

- [@EnriqueSaavedra](https://www.github.com/EnriqueSaavedra)

