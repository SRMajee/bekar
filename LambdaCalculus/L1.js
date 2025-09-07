// λx.x => identity
const a = x => x
// console.log(a(5))

// λx.λy.x+y
const b = x => y => x+y
// console.log(b(1)(5))


/*-----------------church numerals------------------*/
// zero = λf.λx.x
const zero = f=>x=>x 
// one = λf.λx.f(x)
const one = f=>x=>f(x)
const two = f=>x=>f(f(x))
const three = f => x => f(f(f(x)))

// toNumber
// λn.n(λx.x+1)(0)
const toNum = n => n(x=>x+1)(0)
console.log(toNum(three)) 

