(defn merge-sorted-sublists [list-to-merge list-to-merge-2]
  (loop [result-list []
         list-1 list-to-merge 
         list-2 list-to-merge-2]
    (if (or (empty? list-1) 
            (empty? list-2))
      (concat result-list list-1 list-2)
      (if (< (first list-1) (first list-2))
        (recur (conj result-list (first list-1))
               (rest list-1)
               list-2)
        (recur (conj result-list (first list-2))
               list-1 
               (rest list-2)))))) 

(defn merge-sort [list-to-sort]
  (if (= 1 (count list-to-sort))
    list-to-sort
    (apply merge-sorted-sublists 
           (map merge-sort 
                (split-at (/ (count list-to-sort) 2) 
                          list-to-sort)))))
