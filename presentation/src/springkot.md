# Kotlin + Spring 5 
## Two small steps...


##  Jarek Ratajski

@jarek000000

Loves programming since the first line I wrote for C64

Anarchitect @ Engenius GmbH

Java developer (since 1999) with a functional  heart.



## Project


### Disclaimer

Small, good and motivated team can work efficiently with any framework and methodology.

Including Scrum<!-- .element: class="fragment" -->


### Printing

![machine](/src/img/printmachine.jpeg)


- PDF Processing
- Input: tons of PDFs
- Output: one big file (PDF)
- add printing markers
- optimize  resources
- convert to PDFA
- group by document language
- various customers (various steps)


### Non functional

- memory consumption
- failures/ retrials (conversions)
- evolving infrastructure (first: JavaEE.. then... ???)
- basic monitoring
- basic management 


### Reactive streams 

![flux](/src/img/reactivestream.png)

Scala, Akka-streams....<!-- .element: class="fragment" -->



![Reality](/src/img/spring.jpg)

Reality


## Enterprise


![Reality](/src/img/javaee.jpg)

We have JBoss, Websphere, Spring, everywhere....


### Blocking architecture

- 1 request  - 1 thread
- Aspects (Security, Transactions)
- (AKA) Runtime magic


### Transaction
```{java}
@Transactional
    ... User setGroupOwner(String userName, String groupName) {
        final User user = userRepository.findByName(userName);
        final Group group = groupRepository.findByName(groupName);
        user.setGroup(group);
        group.setOwner(user);
        return user;
    }
```   


### What could possibly go wrong?

- method private<!-- .element: class="fragment" -->
- this.method self call<!-- .element: class="fragment" --> 
- bean created by new<!-- .element: class="fragment" -->
- called in other thread<!-- .element: class="fragment" -->
- @Transactional annotation class not deployed<!-- .element: class="fragment" -->
- Evil aspect deployed<!-- .element: class="fragment" -->


### Sad story

No serious business should use frameworks that rely on *runtime magic*


### yet it works...


![fear](/src/img/fear.jpg)


- 198x **C++** after **C**
- 199x **Java** after **C++**
- 196x **UNIX** after **Multics**
 


![steps](/src/img/baby_steps.jpg)




## Step 1
Spring 5


### Most popular Java/Enterprise framework now
![steps](/src/img/spring.png)


### Spring 2.x

![steps](/src/img/xml.jpg)


### Spring 4.x

```{java}
@SuppressWarnings({"unchecked", "rawtypes"})
@Deprecated
@OneToMany(@HowManyDBADoYouNeedToChangeALightBulb)
@OneToManyMore @AnyOne @AnyBody
@YouDoNotTalkAboutOneToMany // Fightclub, LOL
@TweakThisWithThat(
    tweak = {
        @TweakID(name = "id", preferredValue = 1839),
        @TweakID(name = "test", preferredValue = 839),
        @TweakID(name = "test.old", preferredValue = 34),
    },
    inCaseOf = {
        @ConditionalXMLFiltering(run = 5),
    }
)
@ManyToMany @Many @AnnotationsTotallyRock @DeclarativeProgrammingRules @NoMoreExplicitAlgorithms
@Fetch @FetchMany @FetchWithDiscriminator(name = "no_name")
@SeveralAndThenNothing @MaybeThisDoesSomething
@JoinTable(joinColumns = {
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
})
@DoesThisEvenMeanAnything @DoesAnyoneEvenReadThis
@PrefetchJoinWithDiscriminator @JustTrollingYouKnow @LOL
@IfJoiningAvoidHashJoins @ButUseHashJoinsWhenMoreThan(records = 1000)
@XmlDataTransformable @SpringPrefechAdapter
private Collection employees;
```

Copyright annotationmania.com - Lukas Eder


### Spring 5.x

Fact 1 : All that crazy stuff (spring 4.x) still works :-(


### Spring 5.x

Fact 2 : But there is alternative

Spring Web Flux


## Spring WebFlux

Fully *functional* web framework

Similar to Akka-HTTP or NodeJS/Express or Ratpack/Java


```{java}
RouterFunction prepareRouterFunction() {
        return nest( path("/api"),
                    route(GET("/time"), renderTime())
                    .andRoute(GET("/messages/{topic}"), renderMessages())
                    .andRoute(POST("/messages/{topic}"), postMessage()));
}

```

```{java}
private HandlerFunction<ServerResponse> renderTime() {
        return request -> {
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter myFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

            return ServerResponse.ok()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(fromObject(myFormatter.format(now)));
        };
    }

```

No more servlets


No more magic annotations


# Projectreactor


**projectreactor** = yet another reactive streams implementation


WebFlux uses projectreactor streams


```Mono<T>```


```Flux<T>```


### The truth :-)


Spring 5 WebFlux has almost nothing in common with classic Spring


Spring 5 still support *magic and annotations*
  
yuo can avoid that parts   


Unless someone will accidentally use spring-data or other part :-( 



## Java

WebFlux works OK with Java


You can now create *pure* java server applications


### Functional programming with Java


Works


Among language structures  for  fp in Java we have:


Lambdas


.


..


...


...


...


...


...


OK. Seems we only have lambdas.



language with better FP support???


Scala


### Scala problem 


complex


prejudices


*implicits*

:-)


## Step 2 

Kotlin


## Kotlin vs Java

```{java}

public class Document {
    private int id;
    private String path;
    private long type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getType() {
        return type;
    }

    public void setType(long type) {
        this.type = type;
    }
    ///[...]
}

```


No, we don't write JavaBeans anymore.


Java

```{java}
public class Document implements Serializable {
    public final int id;
    public final String path;
    public final long type;

    public Document(int id, String path, long type) {
        this.id = id;
        this.path = path;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Document Document = (Document) o;
        return id == Document.id &&
                type == Document.type &&
                Objects.equals(path, Document.path);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, path, type);
    }

    @Override
    public String toString() {
        return "Document{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", type=" + type +
                '}';
    }

    public Document withId(int newId) {
        return new Document(newId, this.path, this.type);
    }

    public Document withId(String newPath) {
        return new Document(this.id, newPath, this.type);
    }

    public Document withType(long newType) {
        return new Document(this.id, this.path, newType);
    }
}

```
<!-- .element: class="stretch" -->


Kotlin

```{kotlin}
data class Document( val id : Int, val path String, val type : Long)
```


Usage of data classes

```
val newDoc = oldDoc.copy( path = 'aaa bb c');
```


### val 

```{kotlin}
val x = 5

val y = x + 1

//will not work
//x = 7 
```


## if expression

```{kotlin}
val x = if ( y >=0) y else -y  
```


## Basic pattern matching

```{kotlin}
fun main(args : Array<String>) {
    val x : Any = 7
    when(x) {
        in 1..10 -> print("in range")
        is String -> print("I guess it's not even a number")
    }
}
```


## functions / lambdas

```{kotlin}
val a = { i: Int -> i + 1 }

val x:(Int) -> Int = {it + 1}
```


## null safety

```{kotlin}
data class Document( val id : Int, val path: String?, val type : Long)

fun fun1() {
    val doc1 = Document(7, null, 4)
    // will not compile :  val x1:String = doc1.path
    val x1:String  = if (doc1.path!=null ) doc1.path else ""
    // will not compile : val len = doc1.path.length
    val len = doc1.path?.length
    val len2 = doc1.path?.length ?: -42;
}
```


Is it better than Optional/Option ?


Kotlin has own immutable List (and mutable)

Decided to use VAVR (+ kotlin wrapper)<!-- .element: class="fragment" -->

Maybe we should have used Kotlin Lists?<!-- .element: class="fragment" -->


# Kotlin  frameworks/ libs

There is even functional server designed for kotlin (ktor)
(but we keep using Spring 5) 


## Kotlin supports immutability  / expressions

A lot better than Java


## Java++

Seamless java integration (both directions)


Java developer can understand and use Kotlin syntax after short introduction


*15 minutes  and 30 seconds*




##  Or Scala--


No implicits


No  macros


No type projections (#) 


Simple type system


Global type aliases


No *for comprehensions*


As Scala developer you understand kotlin in 5 minutes


It annoys you every couple of minutes


For


There is no for

There are flatMaps


No typeclasses(*)


It looks like Scala, but it is not Scala


## Kotlin success


Great IDE support (IntelliJ)


Google Android - official language (2017)


Spring(Pivotal) - officially supported language (2017)



## Adoption


```{kotlin}
fun processFiles(): Flux<ProcessStatus> = 
         inputFolder.getInputFiles()
                .flatMap(inputFolder::getFile)
                .parallel()
                .doOnNext{ notifyFileScanned(it)}
                .runOn(Schedulers.parallel())
                .flatMap(config.getExtractor()::extract)
                .doOnNext{ notifyExtracted(it)}
                .flatMap(config.getNormalization()::normalize)
                .sequential(8)
                .groupBy(config.getDispatcher()::dispatch)
                .flatMap(this::merge)
                .doOnNext{ notifyMerged(it)}
                .flatMap {
                    val res = ProcessStatus.MergeStep(it)
                    if ( it is MergeStatus.Finished) {
                        Flux.just(res, ProcessStatus.OutputCreated(it.output))
                    } else {
                        res.toMono()
                    }
                }

```


Indifference - accepted


Scepticism - object oriented gurus


Enthusiasms !!!


## JavaEE deployment?


We still can easily deploy code as JavaEE war


We drop functional routers and create classic Spring Rest controller on annotations


Ugly but works



## We have competition


### Lombok


Enterprise answer for Java weaknesses


We will create a new language, but we  pretend we don`t do that


```{java}
@lombok.Getter
@lombok.Setter
@lombok.RequiredArgsConstructor
@lombok.EqualsAndHashCode(of = {"number", "text"})
@lombok.ToString(exclude = "strList")
public class EveryThing {
    private boolean flag;
    private final int number;
    private final String text;
    private List<String> strList;
 
    public List<String> getStrList() {
        if (strList == null) {
            strList = new ArrayList<>(128);
        }
        return Collections.unmodifiableList(strList);
    }
}

```


## Kotlin vs Lombok

| Kotlin  | Lombok |
| ------------- | ------------- |
| New language (syntax) to learn  | New language to learn  |
| Needs build plugin  | Needs build plugin  |
| Good IDE support  | Good IDE support  |
| 15 minutes 30 secs to learn<!-- .element: class="fragment" --> | 15 minutes 7 seconds to learn<!-- .element: class="fragment" -->| 
| Nice syntax<!-- .element: class="fragment" --> | Cancer<!-- .element: class="fragment" --> |



# Summary



Eliminated magic


Practically eliminated mutability


Functional programming of server supported by syntax


Accepted


![steps](/src/img/giant_steps.jpg)



