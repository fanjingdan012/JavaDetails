println "hello groovy"
def i = 18
println (i);
def list  = ['a','b']
list<<'c'
println list.get(2)
def map = ['key1':'v1','k2':'v2']
map.k3='v3'
println map.get('k3')
def b1={
    println 'hello b1'
}
def method1(Closure closure){
    closure()
}
method1 (b1)
def b2={
    v->
        println "hello $v"
}
def method2(Closure closure){
    closure('vvvv')
}
method2(b2)