#lang plai

;; SECOND PART OF ASSIGNMENT


(define-type AE
  [num (n number?)]
  [arith (lhs AE?)
         (op OP?)
         (rhs AE?)])

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

;getOP? takes op and return its procedure
(define (getOp op)
  (second (OP? op)))

(test (getOp '+) +)
(test (getOp '-) -)
(test (getOp '*) *)
(test (getOp '/) /)
(test (getOp '**) expt)
(test (getOp 'mod) modulo)
(test (getOp 'quotient) quotient)

;;parser: sexp -> AE exp
; to convert s-expression into AE expression.

(define (parser sexp)
  (cond
    [(number? sexp) (num sexp)]
    [(and (list? sexp) (OP? (second sexp)) (equal? (length sexp) 3))
     (arith (parser (first sexp))
            (second sexp)
            (parser (third sexp)))]))

(test (parser 5) (num 5))
(test (parser '(3 + 2)) (arith (num 3) '+ (num 2)))
(test (parser '((3 + 2) ** 7)) (arith (arith (num 3) '+ (num 2)) '** (num 7)))
(test (parser '((3 + 2) ** (5 / 1))) (arith (arith (num 3) '+ (num 2)) '** (arith (num 5) '/ (num 1))))


      
;interp : AE-exp -> number
;Evaluate arithmetic operation

(define (inter expr)
  (type-case AE expr
             [num (n) n]
             [arith (l op r) ((getOp op) (inter l) (inter r))]))

(test (inter (parser 2)) 2)
(test (inter (parser '(3 + 2)) 5))
(test (inter (parser '((3 + 2) ** 7))) 78125)
(test (inter (parser '(3 mod 2))) 1)
(test (inter (parser '(63 quotient 12))) 5)
(test (inter (parser '(24 / 12))) 2)
(test (inter (parser '(24 - 10))) 14)
(test (inter (parser '(24 * 3))) 72)
(test (inter (parser '((3 + 2) ** (5 / 1)))) 3125)


   
   

