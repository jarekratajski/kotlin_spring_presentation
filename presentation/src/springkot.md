# Kotlin + Spring 5 
## Two small steps...


##  Jarek Ratajski

@jarek000000

Loves programming since the first line I wrote for C64

Anarchitect @ Engenius GmbH

Java developer (since 1999) with a functional  heart.



## Project


![machine](/src/img/printmachine.jpeg)


- PDF Processing
- Input: tons of PDFs
- Output: one big file (PDF)
- add printing markers
- optimize  resources
- convert to PDFA
- group by document language
- various customers (various steps)


## Non functional

- memory consumption
- failures/ retrials (conversions)
- evolving infrastructure (first: JavaEE.. then... ???)
- basic monitoring
- basic management 


## Reactive streams 

![flux](/src/img/reactivestream.png)



![Reality](/src/img/spring.jpg)

Reality



# This is Java (EE)

We have JBoss, Spring, everywhere....


## Blocking architecture

- 1 request  - 1 thread
- Aspects (Security, Transactions)
- (AKA) Runtime magic


## Scalability ??


## Magic ??


## Sad story

No serious business should use frameworks that relies on *runtime magic*.


## yet it works...



# Step 0
Reactive


## Reactive streams

- retry() 
- cache()
- hot sequences()
- zip()
- merge()

(add example from code)


# Step 1
Spring 5


## Spring WebFlux
Fully *functional* web framework
Similar to Akka-HTTP or NodeJS/Express or Ratpack/Java


(put code example)


##  Ugly parts (of Spring) support
??


# :-)

Spring 5 WebFlux has almost nothing in common with classic Spring.

Spring 5 still support *magic and annotations* but we can completely avoid that parts.   



## Java

WebFlux works great with Java


## Functional programming with Java

Supported. 

We have such things as:

Lambdas


...


...


...


...


...


...


...


OK. Seems we only have lambdas.

# Functional language

Scala

# Scala problem 

- Bad PR

# Step 2 
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

No we dont write like that in Java anymore.


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


```{kotlin}
data class Document( val id : Int, val path String, val type : Long)

// using 
val newDoc = oldDoc.copy( path = 'aaa bb c');
```


## Data classes !


## val 

```{kotlin}
val x = 5

val y = x + 1

//will never work
//x = 7 
```


## if

```{kotlin}
val x = if ( y >=0) y else -y  
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

But is it better than Optional/Option

operators


## Kotlin supports immutability  / expressions


## Java++

Seamless java integration (both directions)


##  Or Scala--

No implicits

No  macros

No type projections (#), 
simple type system


Problems in frameworks:
JavaEE  Spring Domination
Blocking/ Request per Thread
Runtime aspects

Scala - bad PR 
Vavr vs Kotlin