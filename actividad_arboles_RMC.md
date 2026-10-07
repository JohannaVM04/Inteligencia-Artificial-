# Parte I. Conceptos y definiciones

1. ¿Qué es un árbol de decisión y cuál es su objetivo principal dentro de un problema de clasificación?
Es un algoritmo de aprendizaje supervisado que organiza las decisiones y resultados de esas decisiones en una estructura jerárquica, donde su objetivo es predecir las categorias de la variable objetivo utilizando reglas de decisión que divide el espacio de características predictoras en grupos.

2. Explique con sus propias palabras los siguientes elementos de un árbol de decisión:

- Nodo raíz : es donde inicia el arbol, donde parte la primera pregunta que se quiere responder
- Nodo interno: es el nodo que decide qué direccion debe tomar el arbol, si es hacia un subconjunto de nodos o hacia una hoja
- Rama: son las decisiones que se van tomando en el árbol
- Hoja: son los resultados a los que llega el arbol cuando no hay mas nodos

3. ¿Qué es una red neuronal multicapa y qué función cumplen las siguientes capas?

Es un modelo de aprendizaje supervisado que se forma por varias capas de neuronas artificiales conectadas entre si, donde reciben valores numericos, pesos, bias y una función de activación que generará una salida

- Capa de entrada: recibe los datos que se le dan a la red, solo distribuye los datos hacia la siguiente capa
- Capa oculta: cada neurona combina las señales de las capas anteriores, toman sus pesos y utilizan una función de activación no lineal y a partir de esto, se extraen las caracteristicas de los datos
- Capa de salida: produce el resultado fina, dependiendo del numero de neuronas y la funcion de activacion, podemos tener una red neuronal que calcula probabilidades de pertenecer a un grupo, probabilidad que suman 1, o una regresión.

4. Pregunta 4
¿Qué representan los pesos y los sesgos dentro de una red neuronal?
Un peso es un valor que representa la importancia de una conexion entre dos neuronas, es el cuánto afecta al resultado final esa variable. Un sesgo es un valor que desplaza a la función de activacion (ajustar que la neurona se active)

- Explique también por qué sus valores cambian durante el entrenamiento.
Los valores cambian porque al inicio del entrenamuento, son valores dados de forma aleatoria ya que el modelo no sabe resolver lo que le pides y tiene predicciones muy malas, y el entrenamiento es para eso, para corregir esos valores de a poco hasta que el error sea minimo, esto se logra a traves de las épocas.

5. ¿Cuál es la principal diferencia entre la forma en que aprende un árbol de decisión y la forma en que aprende una red neuronal multicapa?
El árbol de decisión aprende buscando reglas, en cada nodo elige qué pregunta puede separar mejor a los datos, a diferencia de una red multicapa, que aprende mediante optimización, ajustando gradualmente los parámetros para minimizar una función de pérdida

Explique qué elementos aprende cada modelo.
**Árbol de decisión** 
Aprende la estructura del árbol, las condiciones de división y los valores de las hojas. La profundidad máxima, el minimo de muestras por nodo y el criterio de división son valores asignados por nosotros, esto no lo aprende por si solo

**Red neuronal multicapa**
Aprende de los pesos y los sesgos de cada neurona, su arquitectura y tasa de aprendizaje se definen, y las representaciones internas de los datos surgen como consecuencia del ajuste de pesos y sesgos.
