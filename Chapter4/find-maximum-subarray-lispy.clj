 
;;;This is another version of find-maximum-subarray
;;;but written in a more lispy way, 
;;;the input would be only a sequence
;;;and the output would just be the subseq itself that has the maximum sum

(defn find-max-crossing-subseq [left right] 
  (apply concat 
         (map (partial apply (partial max-key (partial apply +))) 
              (list (for [left-i (range (count left))] 
                      (drop left-i left)) 
                    (for [right-i (range (count right))] 
                      (drop-last right-i right))))))

(defn find-maximum-subseq [coll] 
  (if (= 1 (count coll))
    coll
    (let [splitted (split-at (/ (count coll) 2) coll)]
      (max-key (partial apply +)
               (find-maximum-subseq (first splitted))
               (find-maximum-subseq (last splitted))
               (find-max-crossing-subseq (first splitted) (last splitted))))))
