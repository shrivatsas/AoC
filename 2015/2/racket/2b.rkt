#lang racket
; Read a file into a list of lines

(define (read-lines filename)
  (with-input-from-file filename
    (λ ()
      (let loop ([lines (list)])
        (let ([line (read-line)])
          (if (eof-object? line)
              (reverse lines)
              (loop (cons line lines))))))))

(define lines (read-lines "2.input"))

(define ribbon 0)
(for ([line lines])
  (define sides (map string->number (string-split line "x")))
  (define tail (cdr (sort sides >)))
  (set! ribbon (+ ribbon (+ (* 2 (foldl + 0 tail)) (foldl * 1 sides)))))

(displayln ribbon)
