# Shirt Drawer
Este repositorio contiene el código asociado con la primera actividad evacuativa de la clase de com\
putación gráfica. Este programa dibuja una camisa a partir de los parámetros decididos por el usuar\
io y ejecuta transformaciones  sobre la misma (trasladar, escalar, rotar).

# Requisitos
Para ejecutar este proyecto, se debe contar con el soporte de java debido a que fue exportado como un archivo '.jar'. Se sugiere tener la siguiente versión de java
```sh
java version "1.8.0_321"
```

# Descarga
Para ejecutar el código	descarge los archivos del directorio denominado 'execute'.
- Si cuentas con sistema operativo windows ejecuta el archivo '.bat'
- De lo contrario ejecuta el archivo '.sh'
- Si los archivos de ejecución no funcionan ejecuta el comando

```sh
user@pc shirtdrawer % java -jar execute/shirtdrawer-1.0-SNAPSHOT.jar
```

## Manual de usuario

La ejecución del proyecto es la siguiente:
1. (por consola) El programa da la bienvenida y solicita las dimensiones asociadas a la camisa
2. (por consola) Antes de presentar la graficación, solicitará las coordenadas donde ubicará la camisa
3. Se despliega el plano y es presentada la camisa con sus dimensiones.
4. (por consola) Se despliega un menú para indicar la siguiente acción a ejecutar:
* Trasladar -> solicita las coordenadas nuevas a donde será trasladada la camisa
* Escalar -> solicita el factor por el que será escalada la figura (use decimales)
* Rotar -> solicita el ángulo (0-360) con el que será rotada la figura 
5. Para finalizar la ejecución, cerrar la ventana de visualización de java.

### _NOTA: La función de rotación solo se matiene 5 segundos, debido a que no se tuvo el tiempo suficiente para implementar la misma gestión de los puntos en las demás funciones_ 