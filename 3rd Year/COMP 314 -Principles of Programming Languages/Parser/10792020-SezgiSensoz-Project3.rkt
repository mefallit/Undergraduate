#lang plai
;list-of-operates is list of list operates
(define list-of-ops
  (list
   (list '+ +)
   (list '- -)
   (list '* *)
   (list '/ /)
   (list '** expt)
   (list 'mod modulo)
   (list 'quotient quotient)))

;sym -> procedure
;take operator, if it is in list it return this list or false
(define (OP? sym)
  (assoc sym list-of-ops))

(test (OP? '+) (list '+ +))
(test (OP? '-) (list '- -))
(test (OP? '*) (list '* *))
(test (OP? '/) (list '/ /))
(test (OP? '**)(list '** expt))
(test (OP? 'mod) (list 'mod modulo))
(test (OP? 'quotient) (list 'quotient quotient))
(test (OP? '>) false)

;getOP? :  symbol -> procedure
;takes op and return its procedure

;(getOp '+) --> +
;(getOp '-) --> -
;(getOp '*) --> *
;(getOp '/) --> /
;(getOp '**) --> expt
;(getOp 'mod) --> modulo
;(getOp 'quotient) --> quotient

(define (getOp op)
  (second (OP? op)))

(test (getOp '+) +)
(test (getOp '-) -)
(test (getOp '*) *)
(test (getOp '/) /)
(test (getOp '**) expt)
(test (getOp 'mod) modulo)
(test (getOp 'quotient) quotient)

; list-of? : question -> boolean
; to understand that given list type based on our parameter

; ((list-of? list?) empty)) -> true
; ((list-of? symbol?) (list 1 2 3)) -> false
; ((list-of? symbol?) (list 'a)) -> true


(define (list-of? t)
  (lambda (x)
    (or (empty? x)
        (and (list? x) (t (first x))
             ((list-of? t) (rest x))))))

(test ((list-of? list?) empty) #t)
(test ((list-of? symbol?) (list 1 2 3)) #f)
(test ((list-of? symbol?) (list 'a)) #t)


;; FonkAE

(define-type Fon
  [num (n number?)]
  [id (id symbol?)]
  [boolean (b symbol?)]
  [arith (lhs Fon?) (op OP?) (rhs Fon?)]
  [olsun (name symbol?) (named-exp Fon?) (body Fon?)]
  [fundef (formal-pars (list-of? symbol?)) (body Fon?)]
  [funapp (app Fon?) (actual-params (list-of? Fon?))]
  [eğer (con Fon?) (true Fon?) (false Fon?)])
  


;parser
; s-exp -> fon-exp
; to conver s-expression to Fon expression

; (parser 3) -> (num 3)
; (parser 'true) -> (boolean 'true)
; (parser 'false) -> (boolean 'false)
; (parser 'a) -> (id 'a)
; (parser '(1 + 2)) -> (arith (num 1) '+ (num 2))
; (parser '((3 + 2) ** 7)) -> (arith (arith (num 3) '+ (num 2)) '** (num 7))
; (parser '((3 + 2) ** (5 / 1))) -> (arith (arith (num 3) '+ (num 2)) '** (arith (num 5) '/ (num 1)))
; (parser '(olsun (x 3) x)) -> (olsun 'x (num 3) (id 'x))
; (parser '(olsun (x 2) false)) -> (olsun 'x (num 2) (boolean 'false))
; (parser '(olsun (x 2) (olsun (y 2) y))) -> (olsun 'x (num 2) (olsun 'y (num 2) (id 'y)))
; (parser '(λ (a) (1 + 2)))) -> (fundef '(a) (arith (num 1) '+ (num 2)))
; (parser '(λ (a b c) a)) -> (fundef '(a b c) (id 'a))
; (parser '((λ (a b) (a + b)) (3 4))) -> (funapp (fundef '(a b) (arith (id 'a) '+ (id 'b))) (list (num 3) (num 4)))
; (parser '(olsun (x (5 + 5)) (x + x))) -> (olsun 'x (arith (num 5) '+ (num 5)) (arith (id 'x) '+ (id 'x)))
; (parser '(olsun (x 5) (x + x))) -> (olsun 'x (num 5) (arith (id 'x) '+ (id 'x)))
; (parser '(olsun (x 5) (olsun (y (x - 3)) ( y + y)))) -> (olsun 'x (num 5) (olsun 'y (arith (id 'x) '- (num 3)) (arith (id 'y) '+ (id 'y))))
; (parser '(olsun (x 5) (x + (olsun (x 3) 10)))) -> (olsun 'x (num 5) (arith (id 'x) '+ (olsun 'x (num 3) (num 10))))
; (parser '(olsun (x 5) (olsun (x x) x))) -> (olsun 'x (num 5) (olsun 'x (id 'x) (id 'x)))
; (parser '(olsun (x 5) (olsun (y x) x))) -> (olsun 'x (num 5) (olsun 'y (id 'x) (id 'x)))



(define (parser sexp)
  (cond
    [(number? sexp) (num sexp)]
    [(or (equal? 'true sexp) (equal? 'false sexp)) (boolean sexp)]
    [(symbol? sexp) (id sexp)]
    [(and (list? sexp) (OP? (second sexp)) (equal? (length sexp) 3))
     (arith (parser (first sexp))
            (second sexp)
            (parser (third sexp)))]
    [(and (list? sexp) (= (length sexp) 3) (equal? 'olsun (first sexp)))
     (olsun (first (second sexp))
           (parser (second (second sexp)))
           (parser (third sexp)))]
    [(and (list? sexp) (equal? (first sexp) 'λ))
     (fundef (second sexp)
             (parser (third sexp)))]
    [(and (list? sexp) (= (length sexp) 2))
     (funapp (parser (first sexp))
             (map parser (second sexp)))]
    [(and (list? sexp) (symbol=? 'eğer (first sexp)) (= (length sexp) 3))
     (eğer (parser (second sexp))
           (parser (third sexp))
           (parser (fourth sexp)))]))

(test (parser 3) (num 3))
(test (parser 'a) (id 'a))
(test (parser 'true) (boolean 'true))
(test (parser 'false) (boolean 'false))
(test (parser '(1 + 2)) (arith (num 1) '+ (num 2)))
(test (parser '((3 + 2) ** 7)) (arith (arith (num 3) '+ (num 2)) '** (num 7)))
(test (parser '((3 + 2) ** (5 / 1))) (arith (arith (num 3) '+ (num 2)) '** (arith (num 5) '/ (num 1))))
(test (parser '(olsun (x 3) x)) (olsun 'x (num 3) (id 'x)))
(test (parser '(olsun (x 2) false)) (olsun 'x (num 2) (boolean 'false)))
(test (parser '(olsun (x 2) (olsun (y 2) y))) (olsun 'x (num 2) (olsun 'y (num 2) (id 'y))))
(test (parser '(λ (a) (1 + 2))) (fundef '(a) (arith (num 1) '+ (num 2))))
(test (parser '(λ (a b c) a)) (fundef '(a b c) (id 'a)))
(test (parser '((λ (a b) (a + b)) (3 4))) (funapp (fundef '(a b) (arith (id 'a) '+ (id 'b))) (list (num 3) (num 4))))
(test (parser '(olsun (x (5 + 5)) (x + x))) (olsun 'x (arith (num 5) '+ (num 5)) (arith (id 'x) '+ (id 'x))))
(test(parser '(olsun (x 5) (x + x))) (olsun 'x (num 5) (arith (id 'x) '+ (id 'x))))
(test (parser '(olsun (x 5) (olsun (y (x - 3)) ( y + y)))) (olsun 'x (num 5) (olsun 'y (arith (id 'x) '- (num 3)) (arith (id 'y) '+ (id 'y)))))
(test (parser '(olsun (x 5) (x + (olsun (x 3) 10)))) (olsun 'x (num 5) (arith (id 'x) '+ (olsun 'x (num 3) (num 10)))))
(test (parser '(olsun (x 5) (olsun (x x) x))) (olsun 'x (num 5) (olsun 'x (id 'x) (id 'x))))
(test (parser '(olsun (x 5) (olsun (y x) x))) (olsun 'x (num 5) (olsun 'y (id 'x) (id 'x))))

    
             
    
    