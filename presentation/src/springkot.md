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

Including Scrum. 


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

Scala, Akka-streams....



![Reality](/src/img/spring.jpg)

Reality


## This is Java (EE)


![Reality](/src/img/javaee.jpg)

We have JBoss, Spring, everywhere....


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

- method private
- this.method self call 
- bean created by new
- called in other thread
- @Transactional annotation class not deployed
- Evil aspect deployed


### Sad story

No serious business should use frameworks that relies on *runtime magic*.


### yet it works...

![fear](/src/img/fear.jpg)


- **UNIX** after **Multics** 196x
- **Linux** ... 199x
- **C++** after **C** 198x
- **Java** after **C++** 199x
 


![steps](/src/img/baby_steps.jpg)




## Step 1
Spring 5


### Most popular Java/Enterprise framework now
![steps](/src/img/spring.png)


### Spring 2.x

![steps](/src/img/xml.jpg)


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

No more servlets.


No more magic annotations.


# Projectreactor


WebFlux uses projectreactor streams.


```Mono<T>```


```Flux<T>```


### :-)

Spring 5 WebFlux has almost nothing in common with classic Spring.


Spring 5 still support *magic and annotations* but we can completely avoid that parts.   


Unless someone will accidentally use spring-data or other part :-( 



## Java

WebFlux works OK with Java


We can now create *pure* java server applications.


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



## Step 2 

Introduce language with better FP support.


Scala


### Scala problem 


complex


prejudices


*implicits*



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


No, we dont write like that in Java anymore.


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


Kotlin

```{kotlin}
data class Document( val id : Int, val path String, val type : Long)
```


Usage of data classes

```
val newDoc = oldDoc.copy( path = 'aaa bb c');
```

### Data classes !


### val 

```{kotlin}
val x = 5

val y = x + 1

//will never work
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


## Kotlin supports immutability  / expressions


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


It looks like Scala, but it is not Scala
  

## Kotlin success


Great IDE support (IntelliJ).


Google Android - official language (2017)


Spring(Pivotal) - officially supported language (2017)





Problems in frameworks:
JavaEE  Spring Domination
Blocking/ Request per Thread
Runtime aspects

Scala - bad PR 
Vavr vs Kotlin