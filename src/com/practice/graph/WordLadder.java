package com.practice.graph;

import java.util.List;
import java.util.Map;
import java.util.*;

public class WordLadder {
    public List<List<String>> findLadders(String beginWord, String endWord, Set<String> wordList) {
        if (wordList == null || wordList.size() == 0) {
            return Collections.EMPTY_LIST;
        }
        String temp = endWord;
        endWord = beginWord;
        beginWord = temp;
        Map<String, List<String>> parent = new HashMap<>();
        Queue<String> queue1 = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Set<String> levelVisited = new HashSet<>();
        List<List<String>> result = new ArrayList<>();
        parent.put(beginWord, null);
        queue1.offer(beginWord);
        visited.add(beginWord);
        boolean foundDestination = false;
        while (!queue1.isEmpty()) {
            while (!queue1.isEmpty()) {
                String word = queue1.poll();
                for (int i = 0; i < word.length(); i++) {
                    char wordArray[] = word.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        wordArray[i] = ch;
                        String newWord = new String(wordArray);
                        if (!endWord.equals(newWord) && (!wordList.contains(newWord) || visited.contains(newWord))) {
                            continue;
                        }
                        List<String> parents = parent.get(newWord);
                        if (parents == null) {
                            parents = new ArrayList<>();
                            parent.put(newWord, parents);
                        }
                        parents.add(word);

                        levelVisited.add(newWord);
                        if (endWord.equals(newWord)) {
                            foundDestination = true;
                            break;
                        }
                    }
                }
            }
            if (foundDestination) {
                break;
            }
            for (String word : levelVisited) {
                queue1.offer(word);
                visited.add(word);
            }
            levelVisited.clear();
        }
        if (!foundDestination) {
            return Collections.EMPTY_LIST;
        } else {
            setParent(parent, beginWord, new ArrayList<>(), endWord, result);
        }
        return result;
    }

    private void setParent(Map<String, List<String>> parent, String startWord, List<String> path, String currentWord,
                           List<List<String>> result) {
        path.add(currentWord);
        if (startWord.equals(currentWord)) {
            result.add(new ArrayList<>(path));
            path.remove(path.size() - 1);
            return;
        }
        for (String p :  parent.get(currentWord)) {
            setParent(parent, startWord, path, p, result);
        }
        path.remove(path.size() - 1);
    }

class Helper{
        String word;
        int dist;
        Helper(String word, int dist) {
            this.word = word;
            this.dist = dist;
        }
}

    //https://leetcode.com/problems/word-ladder/solution/
    public int ladderLength(String beginWord, String endWord, Set<String> wordList) {

        // Since all words are of same length.
        int L = beginWord.length();

        // Dictionary to hold combination of words that can be formed,
        // from any given word. By changing one letter at a time.
        HashMap<String, ArrayList<String>> allComboDict = new HashMap<String, ArrayList<String>>();

        wordList.forEach(
                word -> {
                    for (int i = 0; i < L; i++) {
                        // Key is the generic word
                        // Value is a list of words which have the same intermediate generic word.
                        String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);
                        ArrayList<String> transformations =
                                allComboDict.getOrDefault(newWord, new ArrayList<String>());
                        transformations.add(word);
                        allComboDict.put(newWord, transformations);
                    }
                });

        // Queue for BFS
        Queue<Helper> Q = new LinkedList<Helper>();
        Q.add(new Helper(beginWord, 1));

        // Visited to make sure we don't repeat processing same word.
        HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
        visited.put(beginWord, true);

        while (!Q.isEmpty()) {
            Helper node = Q.remove();
            String word = node.word;
            int level = node.dist;
            for (int i = 0; i < L; i++) {

                // Intermediate words for current word
                String newWord = word.substring(0, i) + '*' + word.substring(i + 1, L);

                // Next states are all the words which share the same intermediate state.
                for (String adjacentWord : allComboDict.getOrDefault(newWord, new ArrayList<String>())) {
                    // If at any point if we find what we are looking for
                    // i.e. the end word - we can return with the answer.
                    if (adjacentWord.equals(endWord)) {
                        return level + 1;
                    }
                    // Otherwise, add it to the BFS Queue. Also mark it visited
                    if (!visited.containsKey(adjacentWord)) {
                        visited.put(adjacentWord, true);
                        Q.add(new Helper(adjacentWord, level + 1));
                    }
                }
            }
        }

        return 0;
    }

    public static void main(String args[]) {
        String[] wordList = {"hot","dot","dog","lot","log"};
        Set<String> wordSet = new HashSet<>();
        wordSet.addAll(Arrays.asList(wordList));
        WordLadder wl = new WordLadder();
        List<List<String>> result = wl.findLadders("hit", "cog", wordSet);
        System.out.print(result);
        wl.ladderLength("hit", "cog", wordSet);
    }
}
