Trabajo Práctico Obligatorio

Programación III - UADE

Enunciado del problema

Juego de Buscaminas: consiste en un tablero T de tamaño NxM cuyas casillas T[i,j] están ocupados por una mina, o por una cifra que indica el número de minas adyacentes en horizontal, vertical o diagonal (el número estará entre 0 y 8). Cuando el juego comienza el contenido de todas las casillas está oculto. En cada jugada se debe escoger una casilla para descubrir su contenido. Si se trata de una mina, el juego termina como “perdido”. Si se trata de una cifra, ésta puede usarse para decidir sobre la posición de las minas; por ejemplo, si se trata de un cero, pueden descubrirse todas las casillas contiguas sin peligro. El juego termina con éxito si se consiguen descubrir todas las casillas sin minas, y sólo esas. El número total de minas m es conocido de antemano por el jugador.

Se debe diseñar un algoritmo que complete el juego en forma exitosa con la menor cantidad de movimientos.

El programa debe recibir un archivo de texto que contenga:
•	La primera línea contiene los siguientes números separados por coma (asumimos que son consistentes con el tablero):
o	N como la cantidad de filas del tablero.
o	M como la cantidad de columnas del tablero.
o	m como la cantidad de minas en el tablero.
•	En  el  resto  de  las  líneas  la  matriz  de  tamaño  NxM  con  un  número  por  casilla  que indique la cantidad de minas adyacentes o una X si en esa casilla hay una mina (cada número o X debe estar separado por un espacio).

Ejemplos de archivos:


Primer archivo: Buscaminas de 8x8 con 10 minas

8,8,10

1 X 2 X 1 1 1 1

1 1 2 1 1 1 X 2

0 0 0 0 0 2 3 X

0 0 0 1 1 3 X 3

0 1 1 2 X 3 X 2

0 1 X 2 2 4 3 2

0 1 1 1 1 X X 1

0 0 0 0 1 2 2 1



Segundo archivo: Buscaminas de 16x16 con 40 minas

16,16,40

X 1 0 0 0 0 0 0 0 0 1 2 2 1 0 0

1 1 1 1 1 0 0 1 1 1 1 X X 1 0 0

0 0 1 X 1 0 0 1 X 1 1 2 2 1 1 1

1 1 2 1 1 0 0 2 2 2 0 0 0 0 1 X

2 X 2 0 0 0 0 1 X 1 0 0 0 0 1 1

2 X 3 1 0 0 0 2 2 2 0 0 0 0 0 0

2 3 X 1 0 1 1 3 X 2 0 0 0 1 1 1

X 3 2 2 0 1 X 3 X 2 0 0 0 1 X 1

1 2 X 1 0 1 1 2 1 2 1 2 1 2 2 2

1 2 3 2 1 0 0 0 0 1 X 2 X 2 2 X

2 X 3 X 2 1 0 0 1 2 2 2 2 X 3 2

2 X 4 3 X 3 3 2 3 X 2 1 2 3 X 1

1 1 3 X 4 X X X 5 X 2 2 X 3 1 1

1 1 3 X 3 3 X X X 2 1 2 X 2 1 1

1 X 3 2 1 1 2 3 2 1 0 1 1 1 1 X

1 2 X 1 0 0 0 0 0 0 0 0 0 0 1 1


Una vez resuelto con éxito, se deberá mostrar en pantalla cuáles fueron los movimientos o casillas descubiertas en el proceso.
