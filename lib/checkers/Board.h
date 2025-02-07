//
// Created by levizor on 10/21/24.
//

#ifndef BOARD_H
#define BOARD_H
#include <set>
#include <vector>


enum Checker {
    blank=0,
    blackman=1,
    whiteman=2,
    blackking=3,
    whiteking=4,
};

enum State {
    moved=0,
    wrong=1,
    blackwon=2,
    whitewon=3,
    tie=4
};

class Board {
public:
    int queue=0;
    int movesWithoutCaptures=0;
    int table[8][8];
    Board();
    int makeMove(std::array<int, 2>& from, std::array<int, 2>& to);

    std::set<std::array<int, 2>> getPossibleActions(std::array<int, 2>);

private:
    std::set<std::array<int, 2>> pendingCaptures;

    std::set<std::array<int, 2>> getPossibleMoves(const std::array<int, 2> &);

    std::set<std::array<int, 2>> getPossibleCaptures(std::array<int, 2>);
    bool moveIsCapture(const std::array<int, 2>& to);
    State getState();
    void capture(std::array<int, 2>& from, std::array<int, 2>& to);
    Checker getChecker(const std::array<int, 2> &from);
    void updatePendingCaptures();
    bool rightTurn(const int& checker);
    bool isBlank(const int& r, const int& c);
    void restart();
};



#endif //BOARD_H
