#lang plai

; <λ-calculus> ::= <id> 
;                | (apt <λ-calculus> <λ-calculus>)
;                | (fun <id> <λ-calculus>)

(define-type λ-calculus
  (id (i symbol?))
  (apt (lhs λ-calculus?) (rhs λ-calculus?))
  (fun (i symbol?) (body λ-calculus?)))


;parser 
; sexp -> l-exp
; to parse s-expression to lambda expression


;(parser 'a) -> (id 'a)
;(parser '(a b)) -> (apt (id 'a) (id 'b))
;(parser '(λ a b)) -> (fun 'a (id 'b))
;(parser '(λ a (λ b (c d)) -> (fun 'a (fun 'b (apt (id 'c) (id 'd))


(define (parser sexp)
  (cond
    ((symbol? sexp) (id sexp))
    ((and (list? sexp) (= (length sexp) 2))
     (apt (parser (first sexp)) (parser (second sexp))))
    ((and (list? sexp) (= (length sexp) 3) (equal? (first sexp) 'λ))
     (fun (second sexp) (parser (third sexp))))
    (else (error "this is not in lambda expression"))))


(test (parser 'a) (id 'a))
(test (parser '(a b)) (apt (id 'a) (id 'b)))
(test (parser '(λ a b)) (fun 'a (id 'b)))
(test (parser '(λ a (λ b (c d)))) (fun 'a (fun 'b (apt (id 'c) (id 'd)))))


;subst
; l-exp l-exp -> l-exp
; substitutes first argument with second argument
; it put's second argument to first argument's free identifier


; (subst (id 'a) (id 'b)) -> (id 'b)
; (subst (id 'a) (apt (id 'a) (id 'b))) -> (apt (id 'a) (id 'b)))
; (subst (id 'a) (fun  'a (id 'b))) -> (fun  'a (id 'b)))
; (subst (apt (id 'a) (id 'b)) (id 'c)) -> (apt (id 'c) (id 'c)))
; (subst (apt (id 'a) (id 'b)) (fun 'c (id 'd)) -> (apt (fun 'c (id 'd)) (fun 'c (id 'd)))
; (subst (apt (apt (id 'a) (id 'b)) (id 'c)) (id 'd)) -> (apt (apt (id 'd) (id 'd)) (id 'd))
; (subst (fun 'a (id 'b)) (id 'c)) -> (id 'c)
; (subst (fun 'a (apt (id 'b) (id 'c))) (id 'd))-> (apt (id 'd) (id d))
; (subst (fun 'a (fun 'a (id 'b))) (id 'd)) -> (id 'd)


(define (subst lexp arg)
  (type-case λ-calculus lexp
             [id (i) arg]
             [apt (lhs rhs) (apt (subst lhs arg) (subst rhs arg))]
             [fun (i body) (subst body arg)]))


(test (subst (id 'a) (id 'b)) (id 'b))
(test (subst (id 'a) (apt (id 'a) (id 'b))) (apt (id 'a) (id 'b)))
(test (subst (id 'a) (fun  'a (id 'b))) (fun  'a (id 'b)))
(test (subst (apt (id 'a) (id 'b)) (id 'c)) (apt (id 'c) (id 'c)))
(test (subst (apt (id 'a) (id 'b)) (fun 'c (id 'd))) (apt (fun 'c (id 'd)) (fun 'c (id 'd))))
(test (subst (apt (apt (id 'a) (id 'b)) (id 'c)) (id 'd)) (apt (apt (id 'd) (id 'd)) (id 'd)))
(test (subst (fun 'a (id 'b)) (id 'c)) (id 'c))
(test (subst (fun 'a (apt (id 'b) (id 'c))) (id 'd)) (apt (id 'd) (id 'd)))
(test (subst (fun 'a (fun 'a (id 'b))) (id 'd))  (id 'd))


;beta-transform

;sexp1 sexp2 -> lexp
; execute beta reduction(transform).

;(beta-transform 'a 'b) -> (id 'b)
;(beta-transform 'a '(a b)) -> (apt (id 'a) (id 'b))
;(beta-transform 'a '(λ a b)) -> (fun 'a (id 'b))
;(beta-transform '(a b) 'a)) -> (apt (id 'a) (id 'a))
;(beta-transform '(a b) '(a b)) -> (apt (apt (id 'a) (id 'b)) (apt (id 'a) (id 'b)))
;(beta-transform '(a b) '(λ a b)) -> (apt (fun 'a (id 'b)) (fun 'a (id 'b)))
;(beta-transform '(λ a b) 'c) -> (id 'c)
;(beta-transform '(λ a (b c)) '(λ a b)) -> (apt (fun 'a (id 'b)) (fun 'a (id 'b)))

(define (beta-transform sexp1 sexp2)
  (subst (parser sexp1) (parser sexp2)))

(test (beta-transform 'a 'b) (id 'b))
(test (beta-transform 'a '(a b)) (apt (id 'a) (id 'b)))
(test (beta-transform 'a '(λ a b))  (fun 'a (id 'b)))
(test (beta-transform '(a b) 'a) (apt (id 'a) (id 'a)))
(test (beta-transform '(a b) '(a b)) (apt (apt (id 'a) (id 'b)) (apt (id 'a) (id 'b))))
(test (beta-transform '(a b) '(λ a b))  (apt (fun 'a (id 'b)) (fun 'a (id 'b))))
(test (beta-transform '(λ a b) 'c) (id 'c))
(test (beta-transform '(λ a (b c)) '(λ a b)) (apt (fun 'a (id 'b)) (fun 'a (id 'b))))

;unparser
;lexp -> sexp
;unparse the given l-expression

(define (unparser lexp)
  (type-case λ-calculus lexp
             (id (i) i)
             (apt (lhs rhs) (append (list (unparser lhs)) (list (unparser rhs))))
             (fun (i body) (append (list 'λ) (list i) (list (unparser body))))))

(test (unparser (id 'a)) 'a)
(test (unparser (apt (id 'a) (id 'b))) '(a b))
(test (unparser (fun 'a (id 'b))) '(λ a b))
(test (unparser (fun 'a (fun 'b (apt (id 'c) (id 'd)))))  '(λ a (λ b (c d))))


; left-most 

; lexp -> sexp
; find the left most lambda expression. than returns unparsed value as a result

; (left-most (id 'a)) -> 'a
; (left-most (apt (id 'a) (id 'b))) -> 'a
; (left-most (beta-transform '(λ a b) '(λ a (λ b (c d))))) -> '(λ a (λ b (c d)))


(define (left-most lexp)
  (type-case λ-calculus lexp
              [id (i) i]
              [apt (lhs rhs) (unparser lhs)]
              [fun (i body) (unparser lexp)]))




