# V Master Keepcoding Mobile.
## Proyecto Módulo Android Avanzado
### Alumno: Jose Luis Bustos Esteban

Caractersticas del desarrollo con Android con Kotlin:

    * Se han usando las mismas vistas para tiendas y Actividades
    * Se ha creado en repositorio y dominio un argumento nuevo "type" donde se puede pasar TypeObject.SHOPS o TypeObject.EVENTS
        ** Segun ese parametro se lanza la url de shops o actividades
        ** El DAO lo tiene en cuenta para añadir una columna mas a la tabla para diferenciar con una misma tabla los registros
        ** El DAO segun el parametro realiza el filtro por este campo a demas de los de clase.
        ** El modelo sigue siendo el mismo SHOPS para todo
    * Al entrar a la aplicación se puede acceder a Shops y Actividad siempre que haya conexion a internet. Se puede eliminar las caches de todo.
    * Al entrar en Shops o Actividades, si no está cacheado, se cachea y se muestra un spinner


Fecha envio proyecto al profesor: 18/02/2018 11:00


Pantallas Android del App:

<img src="https://github.com/joselbe1976/reactNativeMarvelApp/blob/develop/screens/1-load.png" width="180"/> <img src="https://github.com/joselbe1976/reactNativeMarvelApp/blob/develop/screens/2-listado.png" width="180"/> <img src="https://github.com/joselbe1976/reactNativeMarvelApp/blob/develop/screens/3-new.png" width="180"/> 

<img src="https://github.com/joselbe1976/reactNativeMarvelApp/blob/develop/screens/4-detalle.png" width="180"/> <img src="https://github.com/joselbe1976/reactNativeMarvelApp/blob/develop/screens/5-wiki.png" width="180"/> <img src="https://github.com/joselbe1976/reactNativeMarvelApp/blob/develop/screens/6.series.png" width="180"/> 
