#lang plai

;Sezgi Şensöz 
;10792020
;IBU Computer Science

(define-type λ-calculus
  [idd (i symbol?)]
  [apt (lhs λ-calculus?) (rhs λ-calculus?)]
  [def (lambda symbol?) (i symbol?) (body λ-calculus?)])

;parser : sexp -> λ-calculus expression
;Takes s-expression and produce λ-calculus expression

(define (parser sexp)
  (cond
    [(symbol? sexp) (idd sexp)]
    [(and (list? sexp) (equal? (length sexp) 2))
     (apt (parser (first sexp)) (parser (second sexp)))]
    [(and (list? sexp) (equal? (length sexp) 3) (equal? (first sexp) 'λ))
     (def (first sexp) (second sexp) (parser (third sexp)))]))

(test (parser 'a) (idd 'a))
(test (parser '(a b)) (apt (idd 'a) (idd 'b)))
(test (parser '(λ a b)) (def 'λ 'a (id 'b)))
(test (parser '(λ a (b c))) (def 'λ 'a (apt (idd 'b) (idd 'c))))
(test (parser '(λ a (λ b c))) (def 'λ 'a (def 'λ 'b (idd 'c))))
(test (parser '((λ a b) (λ c d))) (apt (def 'λ 'a (idd 'b)) (def 'λ 'c (idd 'd))))

;-----------------------------------------------------------------------------------------------------
;FIRST QUESTION

;list of the unique symbols in a λ-expression.

;in-it? :  X list -> boolean
; Function controls one element in list or not

(define (in-it? x list)
  (cond 
    [(null? list) #f]
    (else 
     (cond 
       ((equal? x (first list)) #t)
       (else (in-it? x (rest list)))))))

(test (in-it? 'a (list 'a 'b 'c)) #t)
(test (in-it? 1 (list 6 2 1 4)) #t)
(test (in-it? 2 (list 1 3 5)) #f)
(test (in-it? 1 '()) #f)

;make-uniq: list(list of something) -> list (uniq list of something)
;This function takes list of something and remove elements which is repetitious(so it will be uniq list)

(define (make_unique list)
     (cond 
       ((null? list) list)
       ((in-it? (first list) (rest list)) (make_unique (rest list)))
       (else (cons (first list) (make_unique (rest list))))))

(test (make_unique (list 1 2 3 1 2 3)) '(1 2 3))
(test (make_unique (list 'a 'a 'a 'a)) '(a))
(test (make_unique '()) '())


;uniq-symbols: λ-expression -> list of uniq symbols
;takes λ-expression and produce uniq list of λ-expression symbols

(define (uniq-symbols lexp)
  (type-case λ-calculus lexp
             [idd (i) (make_unique (list i))]
             [apt (lhs rhs) (make_unique (append (uniq-symbols lhs) (uniq-symbols rhs)))]
             [def (lambda id body) (make_unique (append (list id) (uniq-symbols body)))]))

(test (uniq-symbols (parser 'x)) '(x))
(test (uniq-symbols (parser '(x y))) '(x y))
(test (uniq-symbols (parser '(x x))) '(x))
(test (uniq-symbols (parser '(λ x (x (λ y y))))) '(x y))
(test (uniq-symbols (parser '(λ x (x (λ z y))))) '(x z y))
(test (uniq-symbols (parser '((λ x y) (λ a b)))) '(x y a b))

;--------------------------------------------------------------------------------------------------
;SECOND QUESTION

;Formal Parameters list

;formal-para: λ-expression -> list of symbols
;Takes λ expression and produce list of formal parameters

(define (formal-para lexp)
  (type-case λ-calculus lexp
             [idd (i) '()]
             [apt (lhs rhs) (append (formal-para lhs) (formal-para rhs))]
             [def (lambda id body) (append (list id) (formal-para body))]))


(test (formal-para (parser 'a)) '())
(test (formal-para (parser '(a b))) '())
(test (formal-para (parser '(λ a b))) '(a))
(test (formal-para (parser '(λ a (λ b c)))) '(a b))
(test (formal-para (parser '(λ a (b c)))) '(a))
(test (formal-para (parser '((λ a b) (λ c d)))) '(a c))


;------------------------------------------------------------------------------------------------------

;THIRD QUESTION
;Free identifier

;id -> ()

;remove: list(symbol list) x -> list
;Function remove takes list and symbol, and control is x in list. İf list have x element, function will remove X

(define (remove list a)
  (filter (lambda (x) (not (symbol=? a x ))) list))


(test (remove '() 'a) '())
(test (remove (list 'z 'y 'x) 'y) (list 'z 'x))

;Free Identifier(FI)
; FI(id) -> (id)
; FI(apt) -> FI(lhs) U FI(rhs)
; FI(def) -> FI(body) - id


;free-identifier: λ-expression -> list of symbols
;Takes λ-expression and produce list of free-identifiers

(define (free-identifier lexp)
  (type-case λ-calculus lexp
             [idd (i) (list i)]
             [apt (lhs rhs) (append (free-identifier lhs) (free-identifier rhs))]
             [def (lambda id body) (remove (free-identifier body) id)]))

(test (free-identifier (parser 'x)) '(x))
(test (free-identifier (parser '(x y))) '(x y))
(test (free-identifier (parser '(λ a b))) '(b))
(test (free-identifier (parser '(λ a (λ b c)))) '(c))
(test (free-identifier (parser '(λ a (λ b (x y))))) '(x y))
                         

;------------------------------------------------------------------------------------
;4TH QUESTION

;list of bound identifiers.

;bound-identifier: λ-expression -> list of symbols
;Takes λ-expression and produce list of bound identifiers

(define (bound-identifier lexp)
  (type-case λ-calculus lexp
             [idd (i) (list i)]
             [apt (lhs rhs) (append (bound-identifier lhs) (bound-identifier rhs))]
             [def (lambda id body) (append (list id) (bound-identifier body))]))

(test (bound-identifier (parser 'a)) '(a))
(test (bound-identifier (parser '(a b))) '(a b))
(test (bound-identifier (parser '((λ a b) c))) '(a b c))
(test (bound-identifier (parser '(λ a b))) '(a b))
(test (bound-identifier (parser '(λ a (λ b c)))) '(a b c))
(test (bound-identifier (parser '(λ a (λ b (x z))))) '(a b x z))

;---------------------------------------------------------------------------------
;
;                       
;                       
;  ;  ; ;    ;    ;;;;; 
;  ; ;; ;   ;;    ;     
;  ; ;; ;   ;;    ;     
;  ;;;; ;   ; ;   ;     
;   ;;; ;  ;  ;   ;;;;  
;   ;;; ;  ;;;;   ;     
;   ;; ;   ;  ;;  ;     
;   ;  ;   ;   ;  ;     
;   ;  ;  ;    ;  ;;;;; 
;                       
;                       

;---------------------------------------------------------------------------------

(define-type WAE
  [nmbr (n number?)]
  [id (i symbol?)]
  [arith (op OP?) (lhs WAE?) (rhs WAE?)]
  [with (bound-id symbol?) (named-expr WAE?) (body WAE?)])

;list-of-operates is list of list operates
(define list-of-ops 
  (list 
   (list '+ +)
   (list '- -)
   (list '* *)))

;sym -> procedure
;take operator, if it is in list it return this list or false
(define (OP? sym)
  (assoc sym list-of-ops))

(test (OP? '+) (list '+ +))
(test (OP? '-) (list '- -))
(test (OP? '*) (list '* *))
(test (OP? '/) false)

;getOP? takes op and return its procedure
(define (getOp op)
  (second (OP? op)))

(test (getOp '+) +)
(test (getOp '-) -)

;pars: sexp -> WAE expr
;;to convert s-expression into WAE exp's

(define (pars sexp)
   (cond
     [(number? sexp) (nmbr sexp)]
     [(symbol? sexp) (id sexp)]
     [(and (list? sexp) (OP? (first sexp)) (equal? (length sexp) 3))
           (arith (first sexp)
                (pars (second sexp))
                (pars (third sexp)))]
     [(and (list? sexp)  (equal? 'with (first sexp)))
                    (with (first (second sexp))
                    (pars (second (second sexp)))
                    (pars (third sexp)))]
     (else "Invalid expression")))

(test (pars 5) (nmbr 5))
(test (pars 'a) (id 'a))
(test (pars '(+ a 5)) (arith '+ (id 'a) (nmbr 5)))
(test (pars '(with (x 3) x)) (with 'x (nmbr 3) (id 'x)))
(test (pars '(with (x 3) (with (y 2) (+ x y)))) (with 'x (nmbr 3) (with 'y (nmbr 2) (arith '+ (id 'x) (id 'y)))))

;FI(Free Identifier) 
;;FI(num)->()
;;FI(id)->(id)
;;FI(arith)->FI(lhs) U FI(rhs)
;;FI(with)->FI(bound-body) - bound-id

(define (free-id exp)
  (type-case WAE exp
             [nmbr (n) '()]
             [id (i) (list i)]
             [arith (op l r) (append (free-id l) (free-id r))]
             [with (id bound-value body)
                    (remove (free-id body) id)]))

(test (free-id (pars 5)) '())
(test (free-id (pars 'a)) '(a))
(test (free-id (pars '(+ 1 x))) (list 'x))
(test (free-id (pars '(with (x 3) y))) '(y))

;Bound-id


(define (bound-id exp)
  (type-case WAE exp
             [nmbr (n) '()]
             [id (i) (list i)]
             [arith (op l r) (append (bound-id l) (bound-id r))]
             [with (id val body) (append (list id) (bound-id body))]))
             
(test (bound-id (pars 3)) '())
(test (bound-id (pars 'a)) '(a))
(test (bound-id (pars '(+ 3 1))) '())
(test (bound-id (pars '(with (x 3) y))) '(x y))
(test (bound-id (pars '(with (x 3) x))) '(x x))
(test (bound-id (pars '(with (x 3) (with (y 1) (+ x y))))) '(x y x y))
      
;----------------------------------------------------------------------


;                              
;                              
;  ;  ; ;    ;    ;;;;;  ;;;   
;  ; ;; ;   ;;    ;      ;  ;  
;  ; ;; ;   ;;    ;      ;  ;; 
;  ;;;; ;   ; ;   ;      ;   ; 
;   ;;; ;  ;  ;   ;;;;   ;   ; 
;   ;;; ;  ;;;;   ;      ;   ; 
;   ;; ;   ;  ;;  ;      ;  ;; 
;   ;  ;   ;   ;  ;      ;  ;  
;   ;  ;  ;    ;  ;;;;;  ;;;   
;                              
;                              

;now we dont have any symbol, it will turn to number.
; because of this, id's definition turn to number. 
; We will replace with debruijn index

;
;With (x 3) x

(define-type WAED
  [waed-num (n number?)]
  [waed-id (id number?)]
  [waed-arith (op OP?) (lhs WAED?) (rhs WAED?)]
  [waed-with (named-expr WAED?) (body WAED?)])


;We need to index of list for debruijn

;index: list-of-X X -> number 
; Function takes list and X, produce index of x

(define (index list x)
  (cond
    [(null? list) "variable not found"]
    [(equal? (first list) x) 0]
    (else (cond
     ((in-it? x list)
        (+ 1 (index (rest list) x)))
     (else "variable not found")))))

(test (index (list 1 2 3) 3) 2)
(test (index '() 2) "variable not found")
(test (index (list 'a) 'a) 0)
(test (index (list 'd 'a 'c) 'a) 1)
(test (index (list 'd 'a 'c) 'f) "variable not found")


;WAE-exp bound-id-list -> WAED expr
;takes WAE expressions and produces WAED expressions in which the identifiers are replaced with debuijn indices.

(define (debruijn-helper exp var-list)
  (type-case WAE exp
             [nmbr (n) (waed-num n)]
             [id (i) (waed-id (index var-list i))]
             [arith (op lhs rhs) (waed-arith op (debruijn-helper lhs var-list) (debruijn-helper rhs var-list))]
             [with (id named-exp body) (waed-with 
                                        (debruijn-helper named-exp var-list)
                                        (debruijn-helper body (cons id var-list)))]))

(test (debruijn-helper (pars 5) '()) (waed-num 5))
(test (debruijn-helper (pars 'a) '(a)) (waed-id 0))
(test (debruijn-helper (pars '(+ 3 1)) '()) (waed-arith '+ (waed-num 3) (waed-num 1)))
(test (debruijn-helper (pars '(+ 3 a)) '(a)) (waed-arith '+ (waed-num 3) (waed-id 0)))
(test (debruijn-helper (pars '(with (x 3) x)) '(x)) (waed-with (waed-num 3) (waed-id 0)))
(test (debruijn-helper (pars '(with (x 3) (with (y 2) (+ y x)))) '(x y)) (waed-with (waed-num 3) (waed-with (waed-num 2) (waed-arith '+ (waed-id 0) (waed-id 1)))))


;it is refer to debruijn helper function, it cause to using bound-id list
;Takes WAE exp -> produce WAED exp
(define (debruijn x) (debruijn-helper x (bound-id x)))

(test (debruijn (pars 5)) (waed-num 5))
(test (debruijn (pars 'a)) (waed-id 0))
(test (debruijn (pars '(+ 3 1))) (waed-arith '+ (waed-num 3) (waed-num 1)))
(test (debruijn (pars '(+ 3 a))) (waed-arith '+ (waed-num 3) (waed-id 0)))
(test (debruijn (pars '(with (x 3) x))) (waed-with (waed-num 3) (waed-id 0)))
(test (debruijn (pars '(with (x 3) (with (y 2) (+ y x))))) (waed-with (waed-num 3) (waed-with (waed-num 2) (waed-arith '+ (waed-id 0) (waed-id 1)))))


       
