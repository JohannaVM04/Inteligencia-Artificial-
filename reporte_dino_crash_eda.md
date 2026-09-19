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

- Riesgo si el dataset está mal definido: riesgo medio/alto, porque si el modelo no puede detectar el estado de si estás vivo o muerto, entonces el juego simplemente se hará una especie de juego trol que te mata a su antojo y te mantiene vivo por bugs

### P2 — ¿Cuántos puntos alcanzará esta partida al morir?

- Y: variable entera, un contador de puntos que se detiene cuando el estado (muere o no) cambia a que si murió
- X (mínimo 5 variables):
    1. velocidad_actual : a qué velocidad llegaste hasta ese punto del juego (al morir)
    2. distancia_total: cuál fue la distancia final que recorriste en el juego
    3. num_obstaculos: cuántos obstáculos pasaste exitosamente
    4. puntos_actuales: cuántos puntos acumulados llevas hasta ahí
    5. tiempo_transcurrido: cuánto tiempo llevas jugando esa partida (si hay una relacion entre la distancia y el tiempo)

- Granularidad: una partida completa, porque solamente vamos a evaluar el valor final, y, si ponemos frame por frame, constantemente va cambiando de valor y es basura para el dataset

- Tamaño mínimo de dataset: como este problema es de regresión, entonces hay más variabilidad en los resultados que nos pueden dar, asi que lo más óptimo serian unas 150-200 partidas, porque necesitamos que el dataset tenga datos de jugadores muy buenos(los expertos), asi como los muy malos (los que se mueren en el primer osbtáculo)

- Riesgo si el dataset está mal definido: tomando de referencia el punto 4 (Tamaño minimo del dataset), seleccionar solamente una forma de jugar (ser muy bueno o ser muy malo) hará que el modelo en lugar de aprender que un jugador puede tener una puntuación muy baja o muy alta, simplemente esperará un tipo de jugador y fallará al encontrarse con una excepción (cuando en realidad aprendió una excepción). Riesgo bajo/medio


### P3 — ¿Qué tipo de obstáculo viene próximo?

- Y: tipo_obstaculo (un arreglo), porque no existe ni uno ni dos tipos de obstaculos, y hay variaciones del mismo obstaculo 
- X (mínimo 5 variables):
    1. velocidad_juego: la velocidad a la que va aumentando el juego conforme avanza, porque tambien va cambiando qué obstaculos salen
    2. puntuacion_actual : como se van desbloquendo obstaculos desde cierto punto, entonces necesitamos esa variable para saber de qué más debe preocuparse el modelo (primero son solo cactus, luego salen los pajaros?/algo que vuela)
    3. obstaculo_anterior: para saber la probabilidad de que salga un obstaculo igual o diferente al que acabamos de pasar
    4. tiempo_obstaculo: cuántos frames/tiempo hay entre el obstaculo que acaba de pasar y el que vamos a pasar ahora (si es que hay)
    5. posicion: a cuántos frames viene el siguiente obstaculo porque asi le da tiempo a decidir si debe saltar, agacharse o avanzar más

- Granularidad: un evento (cada vez que aparece un obstaculo), porque si ponemos frame a frame, muchos quedarán vacios o sin informacion relevante para esta parte especifica del dataset,y lo que necesitamos es que aprenda los tipos

- Tamaño mínimo de dataset: al parecer, existen 6 categorias de obstaculos, entonces deberiamos tener unos 50-100 muestras de cada categoria para que se haga un modelo básico.

- Riesgo si el dataset está mal definido: medio/alto. Si solo agarramos a jugadores malos, entonces el dataset se va a llenar de puros cactus basicos (uno y de los que son bajitos), y si avanza más allá de esos puntajes (puntajes muy bajos), entonces no sabrá qué hacer si se topa con otra categoria de obstáculo
