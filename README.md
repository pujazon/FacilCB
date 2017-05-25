# FacilCB
Esta es la aplicación de software libre para la assignatura de SLDS. Tiene las siguientes funcionalidades principales:
Estan explicadas de izquierda a derecha y de arriba a abajo en el orden que aparecen los iconos en el menú:

(!) Hay una clase que es PROVAS la cual no se peude ver a la hora de usar la app, que muestra (si se empezara el Activity)
las coordenadas donde está, para comprovar el funcionamiento del IPS. 

1.El botón del documento, es la opción MySquad. Al pulsarla se abre un activity que tiene un botón "get qsuads" al apretarlo aparecerán las alineaciones del partido del equipo local y visitante. Esto lo hemos hecho estableciendo una conexión FTP con un Servidor que hemos creado (gratiuto, con hostinger.es) y en el Servidor hay un fichero de texto que contiene las alineaciones de cada equipo, de manera que solo con modificar en cada partido ese fichero la aplicación sacará las diferentes alineaciones para los diferentes partidos.

2. El botón del mapa despiega el sistema de IPS. Utilizando la SDK de indoorAtlas carga el mapa por un KEY_ID, luego hace la conexión a la API con sus respectivos KEYS y combinado con una classe llamada BlueDot, que es la posición del móvil, muestra el mapa y nuestra posición. El punto será mas grande en función de la precisión del mapa, es decir a más grande i más espacio más precisión. 

3. El botón del GPS activa el GPS de la API de Google Maps.

4. El botón de ajustes no es más que una página de texto plano que exlica cada una de las funcionalidades de la app.

5. El último botón, el del mensaje, permite hacer una llamada con un número de atención (un servidor).
