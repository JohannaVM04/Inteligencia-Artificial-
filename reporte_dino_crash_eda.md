# Reporte EDA — Operación Dino Crash
**Analista:** [Johanna Velasco | 22121290]

## 1. Problema y dataset (Misión 1)
### P1 — Muerte en el siguiente frame

- Y: definir si muere = 0 , si no muere = 1 (una variable binaria)
- X (mínimo 5 variables):
    1. posicion_x: la posición horizontal donde se encuentra el dinosaurio
    2. posicion_ y : la posicion vertical donde se encuentra el dinosaurio 
    3. velocidad_y : saber si el dinosaurio está subiendo o bajando y qué tan rápido lo está haciendo  
    4. velocidad_mapa : la velocidad a la que va el juego en ese momento 
    5. tamaño_obstaculo: qué tan ancho es el obstaculo que va a saltar o agachar (varios cactus continuos, por ejemplo)
    6. altura_obstaculo: qué tan alto es el obtaculo que va a saltar o agachar
    7. relacionDino_obs : qué tanta distancia existe entre el dinosaurio y el obstáculo 
    8. puntuacion_a : cuántos puntos lleva en ese momento del juego 
    9. salta_o_no : una variable binaria para saber si el dinosaurio está saltando o no 
 
    
- Granularidad: un frame cada 16 ms, porque se necesita revisar constantemente el estado en el que está el dino para saber si el juego continua o no (si la variable de estao "vive o muere" cambió o se mantuvo igual)

- Tamaño mínimo de dataset: aprox 25 muestras para crear el modelo inicial (aunque si debe hacerse una buena selección de las muestras que vamos a usar , porque si los frames entre si son muy parecidos, analizar todos como casos especificos no funcionaría para crear los casos de uso. Por ejemplo, si en varias partidas tenemos al dinosaurio en 50 frames sin obstaculos cerca y tiene solo 2 frames saltando un cactus, entonces el modelo se hará experto en detectar el primer estado, pero el de saltar tendrá muchos errores), y más de 100 intentos para que comience a considerarse confiable, ya que no podemos saber si hay sesgo en el tiempo de reacción (cada salto o cada vez que se agacha)

### P2 — ¿Cuántos puntos alcanzará esta partida al morir?

- Y: variable entera, un contador de puntos que se detiene cuando el estado (muere o no) cambia a que si murió
- X (mínimo 5 variables):
    1. velocidad_actual : a qué velocidad llegaste hasta ese punto del juego (al morir)
    2. distancia_total: cuál fue la distancia final que recorriste en el juego
    3. num_obstaculos: cuántos obstáculos pasaste exitosamente
    4. puntos_actuales: cuántos puntos acumulados llevas hasta ahí
    5.  

- Granularidad:
- Tamaño mínimo de dataset: