# 人工知能の授業課題：8パズル問題、15パズル問題
### 概要
人工知能の授業でランダムな初期配置から8パズル問題を解くプログラムを実装するという課題に取り組んだ。  
反復深化探索、A* 探索（ヒューリスティック関数を3種類変える）のアルゴリズムを実装し、探索効率を調べた。  
15パズルは任意の課題だったが、途中であきらめることができず、最後まで取り組んだ。  
完全にランダムだと解けない初期配置になってしまうため、初期配置を確認し、解けないものならもう一度配置し直すというようにした。
### 使用言語
Java
### プログラムの内容
* NPC.java、NPCEi.java、NPCFif.java：現在の盤面・親の盤面・コストなどを保持するクラス（A* 探索用）
* AStarEi.java、AStarFif.java： A* 探索に関する機能を集めたクラス
* Puzzle.java、EightPuzzle.java、FifteenPuzzle.java：初期配置を決定・盤面の状態が一致しているか、解けない初期配置になっていないか確認するクラス
* DLS.java：深さ制限探索を行うクラス
* IDS.java：DLS.javaを利用しながら反復深化探索を行うクラス
* NPD.java：現在の盤面・親の盤面・現在の深さなどを保持するクラス（反復深化探索用）
### 工夫した点
* コードを重複して書かなくてよいよう、8パズルと15パズルで共通の関数は、親のクラスにまとめた
### 課題
かなり冗長なコードとなってしまっている
