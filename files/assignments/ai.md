# AI and Machine Learning class

## Introduction

In this document we'll explore what is AI and Machine Learning, how it started and what are their applications. 
We'll start by giving a simple **Overview**, where we'll talk about the history of it, give some examples of daily apps we use that implement some form of AI and machine learning and talk about the differerent types of AI and AI algorithms.

Next, we'll dive into some **AI algorithms** in both the area of Searching (Procura informada e não informada) and Learning (Aprendizagem por reforço e Aprendizagem por exemplos) from which point we'll procede for **Machine Learning 101**. 

After that, we'll expose some **Examples related to the Health sector** and lastly give some practical examples of **Useful resources** to implement Machine Learning in your app such a couple of Python Libs and DeepLearning4j.

We hope that this class contributes to wonder you with the amazing world of AI and at the same time empower you with some great tools that might be useful for your project. 


*Note: Some terms are in Portuguese to help the original classroom better understand it.*

## Overview

### History
#### Prehistory of AI

A AI tem bases em diversas áreas do conhecimento tais como :

- **Filosofia**

  Lógica, raciocínio, mente como sistema físico, fundamentos de aprendizagem, linguagem, racionalidade.

- **Matemática**

  Representação formal e algoritmos, provas, computação, probabilidade.
  
- **Economia**

  Utilidade, investigação operacional.
  
- **Neurologia**

  Componente física da actividade mental.

- **Psicologia**

  Fenómeno da percepção e do controlo motor, técnicas experimentais.
  
- **Engenharia de computadores**

  Computadores eficientes.

- **Controlo**

  Desenho de sistemas que maximizam uma função objectivo ao longo do tempo.

- **Linguística**

  Representação, gramática.

#### Modern history of AI

- **1943**      McCulloch & Pitts: modelo do cérebro usando um circuito Booleano

- **1950**      Artigo Turing "Computing Machinery and Intelligence" : define a questão "Can machines think?"

- **1956**      Encontro de Dartmouth: o campo da “Inteligência Artificial" é estabelecido.

- **1950s**     Primeiros sistemas da IA, incluindo jogo de xadrez de Samuel, demonstrador de teoremas de Newell & Simon, engenho de geometria de Gelernter

- **1965**      Algoritmo completo para raciocínio lógico de Robinson

- **1966—73**   IA descobre complexidade computacional

- **1969—79**   Início dos sistemas baseados em conhecimento

- **1980--**    IA como indústria 

- **1987--**    IA como ciência 

- **1995--**    Agentes inteligentes 




### Definitions

#### Global definition

"It's the science and engineering of making intelligent machines."

"The field of study that gives the ability to learn without being explicitly programmed."

#### 4 definitions 

- **Machines with minds (Pensar como os humanos)**
  
  Associada à “Revolução do conhecimento” em 1960 com a Psicologia e a Neurologia.
  
  Ciências cognitivas combinavam modelos computacionais de IA com técnicas experimentais da Psicologia.

- **Study of mental faculties through the use of computational models (Pensar racionalmente)**
  
  Aristóteles foi um dos primeiros a tentar codificar o “pensamento correcto”.
  
  Matemáticos do século XIX desenvolveram notações lógicas precisas para representar objectos e relações entre objectos
  
  Desde 1965 existem “provadores de teoremas” que, teoricamente, conseguiriam resolver todos os problemas desde que estes fossem codificáveis numa notação lógica.

- **Create machines that perform functions that require intelligence when performed by people (Atuar como os humanos)**
  
  Turing (1950) "Computing machinery and intelligence": “As máquinas podem pensar?" -> “As máquinas podem ter um comportamento inteligente?"
  
  Nunca nenhuma máquina conseguiu passar o teste de Turing (ainda).
  
  <img src="https://cdn.shortpixel.ai/spai/w_977+q_lossy+ret_img+to_webp/https://mk0iaexpertacadlbryk.kinstacdn.com/wp-content/uploads/2016/07/AlanTuring-otestedeturing.png" alt="turing" width="500"/>
  
  
  <img src="https://geekandpoke.typepad.com/geekandpoke/images/2008/04/24/turingtest.jpg" alt="turingcomic" width="300"/>

- **Rational behaviour in artifacts (Atuar racionalmente)**
  
  Encontrar a decisão correcta: aquela que maximiza a expectativa de alcançar um objectivo, em função da informação disponível. Não implica necessariamente uma       decisão racional (por exemplo no caso dos reflexos humanos podem ser "ativados" mesmo que não sejam fulcralmente necessários mas o pensamento está subjacente a     uma decisão racional).

### Daily use examples

#### Fiction

- **Data(Star-Trek)**

 <img src="https://vignette.wikia.nocookie.net/memoryalpha/images/9/94/You_are_not_my_mother.jpg/revision/latest?cb=20111116232857&path-prefix=en" alt="data" width="300"/>

- **J.A.R.V.I.S (MCU)**

 <img src="https://vignette.wikia.nocookie.net/universocinematograficomarvel/images/b/b0/JuARaVeInSy.png/revision/latest?cb=20150919150102&path-prefix=pt" alt="jarvis" width="300"/>

 <img src="https://img.cinemablend.com/filter:scale/quill/5/3/c/4/0/5/53c405592e11ae6dc5956d3a26aadd25d004ad80.jpg?fw=1200" alt="vision" width="300"/>

- **C3PO and R2D2 (Star Wars)**

 <img src="https://s2.glbimg.com/WJoBA31T50iBoho7fljoeEjeTeM=/smart/e.glbimg.com/og/ed/f/original/2015/09/23/untitled-1_1.jpg" alt="StarWars" width="300"/>

- **HAL (2001 Space Odyssey)**

 <img src="https://static01.nyt.com/images/2018/05/15/arts/01hal-voice1/25hal-voice1-facebookJumbo.jpg" alt="HAL" width="300"/>

- **KITT (Knight Rider)**

<img src="https://www.scalemates.com/products/img/9/7/7/542977-18821-54-pristine.jpg" alt="HAL" width="300"/>

- **Samantha (Her)**

<img src="https://miro.medium.com/max/2480/1*3URV6TzrVQnfPlpgMO7WNg.jpeg" alt="Samantha" width="300"/>


#### Real-world

- **IBM Watson Jeopardy Player**

<img src="https://i.ytimg.com/vi/P18EdAKuC1U/maxresdefault.jpg" alt="Watson" width="300"/>

- **Infinite Mario AI player**

<img src="https://i.ytimg.com/vi/DlkMs4ZHHr8/hqdefault.jpg" alt="Watson" width="300"/>

- **[Deep-Q Atari Player](https://www.youtube.com/watch?v=V1eYniJ0Rnk)**

- **AlphaGo**

<img src="https://miro.medium.com/max/1200/1*3DvSjCLORuexj3Y-G-r8hw.jpeg" alt="alphago" width="300"/>

- **Elon Musk's OpenAI DOTA Player**

<img src="https://techcaption.com/wp-content/uploads/2017/09/Elon-Musks-AI-Just-Beat-The-Worlds-Best-DOTA-2-Players.png" alt="dota" width="300"/>

- **[Table-tennis Playing Quadcopter](https://www.youtube.com/watch?v=YvbHXz3lccc)**

- **CMU's TARTAN Racing Autonomous Vehicle**

<img src="https://lh3.googleusercontent.com/proxy/-aRbVhoHSrM0uRP8-0q3t2X65xCwkiGYWcx-yMdR86yfW3QkhJe6OcVLbvQSV-8s4aVigYblg816AttQEZIVk32yoonK" alt="car" width="300"/>

- **CS GO bots**

<img src="https://i.ytimg.com/vi/Oj3WtIY6Gvo/maxresdefault.jpg" alt="csgo" width="300"/>


- **Spotify recomendation algorithm**

<img src="https://thumb.spokesman.com/GsJALbsxruDIAgPJdGdubb-x00I=/2500x0/media.spokesman.com/photos/2020/07/15/5f0dee5554d81.hires.jpg" alt="spotify" width="300"/>

- **Netflix recomendation algorithm**

<img src="https://github.com/miquelroq/health-app/blob/master/files/resources/sherlock.png" alt="netflix" width="300"/>

- **Google personalized ADs**

<img src="https://storage.googleapis.com/gweb-uniblog-publish-prod/images/UAC_Blog_8_14_-_Image_3.-_FINAL.max-1000x1000.jpg" alt="google" width="300"/>

## AI algorithms

Necessidade inicial de definir o problema de forma a poder depois criar um agente que o resolva.

### Searching

The possible solutions for any problem usually come in a set of possible action sequences. These, starting at the initial state form a search tree with the initial state at the root, branches as actions and nodes as states. To consider taking an action we expand the current state, that is, we apply each legal action to the current state, thereby generating a new set of states.

#### Blind search

- **BFS (Procura em Largura Primeiro)**

  ![BFS](https://upload.wikimedia.org/wikipedia/commons/5/5d/Breadth-First-Search-Algorithm.gif)

- **DFS (Procura em Profundidade Primeiro)**

  ![DFS](https://upload.wikimedia.org/wikipedia/commons/7/7f/Depth-First-Search.gif)

- **Uniform cost search (Procura de Custo-uniforme)**

  <img src="https://static.javatpoint.com/tutorial/ai/images/uniform-cost-search-algorithm.png" alt="UCS" width="500"/>
  
- **DLS (Procura em Profundidade Limitada)**

  <img src="https://static.javatpoint.com/tutorial/ai/images/depth-limited-search-algorithm.png" alt="DLS" width="500"/>
  
- **Iterative deepening DFS (Procura em Profundidade iterativa)**
  
  <img src="https://i.stack.imgur.com/Y5iyJ.png" alt="IDFS" width="500"/>
  
- **Bidirectional search (Procura Bidirecional)**

  <img src="https://static.javatpoint.com/tutorial/ai/images/bidirectional-search-algorithm.png" alt="IDFS" width="500"/>

#### Informed search

We  consider  as  a  general  approach  the best-first  search,  in  which  a  node  is  selected  for expansion based on an evaluation function f(n). It’s constructed as cost estimate so the node with  the  lowest  evaluation  is  expanded  first,  and  a  priority  queue  is  used.  

A  lot  of  problems consider an heuristic function, h(n), which is the estimated cost of the cheapest path from the state at node n to a goal state, as a component of f(n). h(n) takes a node as input, but considers only its state.

- **Greedy best-first search**

  Tries to **expand the node that s closest to the goal**, on the grounds that this is likely to lead to a solution  quickly. **Evaluates  nodes  by  using  solely the heuristic  function.**  
  
  The search  cost  is minimal, since this search never expands a node that is not on the solution path however it is not optimal, since it  might not consider better alternatives –in every step it tries to get as close to the goal as it can.
  
  The tree search version is also incomplete, much for the same reasons. We are facing O(bm), although with a good heuristic this can be reduced significantly.

- **A*search**

  Minimizing the total estimated solution cost.
  F(n) = g(n) [cost to reach the node] + h(n) [cost to get from the node to the goal]
  
  Conditions for optimality include that **h(n) must be an admissible heuristic which means that it never overestimates the cost to reach the goal**. It must therefore  be optimistic because it will often  think  the  cost  of  solving  the  problem  is  less  than  it  is.  Other  condition,  only  applied  to graph search, is  that **h(n) must  be consistent which happens f for every node n and for every successor n’generated  by  an  action a the  estimated  cost  of  reaching  the  goal  from n is  not greated than the step cost of getting to n’plus the estimated cost of reaching the goal from n’**. Every consistent heuristic is also admissible.

- **IDA* Memory-bounded heuristic search **
  
  IDA*  emerges  with  the  idea  of  adapting  **iterative  deepening  search  to  the  heuristic  search context**. The cutoff used is the f-cost rather than depth, and therefore at each iteration the cutoff value is the smallest f-costof any node that exceeded the cutoffof the previous iteration.
  
  ![IDA*](https://image.slidesharecdn.com/lecture-17iterativedeepeningastaralgorithm-170131122402/95/lecture-17-iterative-deepening-a-star-algorithm-4-638.jpg?cb=1485865497)
  

## Machine Learning 101

//TODO: add stuff

## Examples related to the Health sector

- [Google Health TED talk](https://www.youtube.com/watch?v=MNp26DgKxOA&list=PL590L5WQmH8e3dS9CtvRofb0nfdGb-Of9&index=2&t=0s)

- [How useful is artificial intelligence (AI) in medical research?](https://www.srgtalent.com/blog/how-useful-is-ai-in-medical-research)

## Useful resources

- Python lib 1 //TODO: add stuff

- [Deeplearning4j](https://deeplearning4j.org/) //TODO: add stuff

- Python lib 2 //TODO: add stuff

## More links you might want to check 

- [Google health playlist](https://www.youtube.com/playlist?list=PL590L5WQmH8e3dS9CtvRofb0nfdGb-Of9)

- [IST IArt class slides](https://www.edenbox.org/index.php/s/edenbox?path=%2FLEIC%2F3%C2%BA%20Ano%2F1%C2%BA%20Semestre%2FIA%2FTe%C3%B3ricas)

- [How Machine Learning is Building Super Mario Levels](https://towardsdatascience.com/mariomakers-8a67b25866dd)

- [7 types of AI](https://www.forbes.com/sites/cognitiveworld/2019/06/19/7-types-of-artificial-intelligence/)

## Authors

- [Miguel Roque](https://www.github.com/miquelroq)

- [Tiago Melo](https://www.github.com/tiagoleonmelo)