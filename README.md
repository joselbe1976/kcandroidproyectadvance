# V Master Keepcoding Mobile.
## Proyecto Módulo Android Avanzado
### Alumno: Jose Luis Bustos Esteban

Caractersticas del desarrollo con Android con Kotlin:

    * Al entrar a la aplicación se puede acceder a Shops y Actividad siempre que haya conexion a internet. Se puede eliminar las caches de todo
    * Al entrar en Shops o Actividades, si no está cacheado, se cachea y se muestra un spinner  
    * Se han usando las mismas vistas para tiendas y Actividades
    * Uso de la libreria ANKO de Kotlin
    * Se ha creado en repositorio y dominio un argumento nuevo "type" donde se puede pasar TypeObject.SHOPS o TypeObject.EVENTS
        ** Segun ese parametro se lanza la url de shops o actividades
        ** El DAO lo tiene en cuenta para añadir una columna mas a la tabla para diferenciar con una misma tabla los registros
        ** El DAO segun el parametro realiza el filtro por este campo a demas de los de clase.
        ** El modelo sigue siendo el mismo SHOPS para todo
        ** El modelo Cache para la eliminacion de todas, no se ha tenido en cuenta el type, con el fin de borrar toda la tabla con una única llamada, aunque queda preparado para poder borrar unas u otras, porque llega el parametro.
    
   

Fecha envio proyecto al profesor: 18/02/2018 11:00


Pantallas Android del App:

<img src="https://github.com/joselbe1976/kcandroidproyectadvance/blob/master/imagesGit/menu.png" width="180"/> <img src="https://github.com/joselbe1976/kcandroidproyectadvance/blob/master/imagesGit/shops.png" width="180"/> <img src="https://github.com/joselbe1976/kcandroidproyectadvance/blob/master/imagesGit/shopsdetail.png" width="180"/> 

<img src="https://github.com/joselbe1976/kcandroidproyectadvance/blob/master/imagesGit/eventos.png" width="180"/> <img src="https://github.com/joselbe1976/kcandroidproyectadvance/blob/master/imagesGit/evetnosDetail.png" width="180"/> 
