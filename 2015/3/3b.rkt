#lang racket

(define walk (string-append (file->string "3.input")))

(define (stringify pos)
  (string-append (number->string (car pos)) (number->string (cdr pos))))

(define posA (cons 0 0))
(define posB (cons 0 0))

(define visited (set))
(set! visited (set-add visited (stringify posA)))

(define (move pos c)
    (cond
    [(char=? c #\^) (cons (car pos) (+ (cdr pos) 1))]
    [(char=? c #\v) (cons (car pos) (- (cdr pos) 1))]
    [(char=? c #\>) (cons (+ (car pos) 1) (cdr pos))]
    [(char=? c #\<) (cons (- (car pos) 1) (cdr pos))]
    [else (error "Unexpected character" c)]))

(for ([idx (in-naturals)] [c walk])
  (if (even? idx)
    (begin
      (set! posA (move posA c))
      (set! visited (set-add visited (stringify posA))))
    (begin
      (set! posB (move posB c))
      (set! visited (set-add visited (stringify posB))))))

(displayln (set-count visited))