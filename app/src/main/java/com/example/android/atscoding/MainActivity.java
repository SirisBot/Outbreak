package com.example.android.atscoding;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public static void main(String[] args) {

        Room[][] verticalTrue = new Room[][] {  {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(true), new Room(false), new Room(true), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) } };

        Room[][] horizontalTrue = new Room[][] { {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(true), new Room(true), new Room(true), new Room(true), new Room(true), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) },
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) } };

        Room[][] noInfection = new Room[][] { {new Room(true), new Room(false), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(true), new Room(false), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(true), new Room(false), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(true), new Room(false), new Room(true), new Room(false), new Room(true), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(true), new Room(false), new Room(false), new Room(true), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(true), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(true), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) }, {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false), new Room(false) } };

        System.out.println(isOutbreak(noInfection));
    }

    public static class Room {
        public final boolean isInfected;
        public boolean visited = false;

        Room (boolean infected) {

            isInfected = infected;
        }
    }

    public static boolean isOutbreak(Room[][] floor) {
        //number of connected infected rooms
        int iCount = 0;

        //hardcoded the length and width for debugging
        int rows = 10;
        int columns = 9;

        boolean foundInfected = true;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {

                iCount = 0;

                //find the first room with signs of infection
                if (floor[i][j].isInfected) {
                    iCount++;
                    floor[i][j].visited = true;

                    //navigate connecting rooms and find cases of infection
                    //only loop if infection found in surrounding area

                    while (foundInfected) {

                        foundInfected = false;

                        //5 cases found signifying outbreak
                        if (iCount >= 5) {
                            return true;
                        }

                        if (i == 0) {
                            if (j == 0) {
                                //inspect right
                                if (floor[i][j+1].isInfected) {
                                    //only add case of outbreak if room hasn't been searched
                                    if (!floor[i][j+1].visited) {
                                        iCount++;
                                    }
                                    floor[i][j+1].visited = true;
                                    j++;
                                    foundInfected = true; //found signs of infection
                                }
                                //inspect below
                                else if (floor[i+1][j].isInfected) {
                                    if (!floor[i+1][j].visited) {
                                        iCount++;
                                    }
                                    floor[i+1][j].visited = true;
                                    i++;
                                    foundInfected = true;
                                }
                            }
                            else if (j == 8) {
                                //inspect left
                                if (floor[i][j-1].isInfected) {
                                    if (!floor[i][j-1].visited) {
                                        iCount++;
                                    }
                                    floor[i][j-1].visited = true;
                                    j--;
                                    foundInfected = true;
                                }
                                //inspect below
                                else if (floor[i+1][j].isInfected) {
                                    if (!floor[i+1][j].visited) {
                                        iCount++;
                                    }
                                    floor[i+1][j].visited = true;
                                    i++;
                                    foundInfected = true;
                                }
                            }
                            else {

                                //inspect right
                                if (floor[i][j+1].isInfected) {
                                    if (!floor[i][j+1].visited) {
                                        iCount++;
                                    }
                                    floor[i][j+1].visited = true;
                                    j++;
                                    foundInfected = true;
                                }
                                //inspect left
                                else if (floor[i][j-1].isInfected) {
                                    if (!floor[i][j-1].visited) {
                                        iCount++;
                                    }
                                    floor[i][j-1].visited = true;
                                    j--;
                                    foundInfected = true;
                                }
                                //inspect below
                                else if (floor[i+1][j].isInfected) {
                                    if (!floor[i+1][j].visited) {
                                        iCount++;
                                    }
                                    floor[i+1][j].visited = true;
                                    i++;
                                    foundInfected = true;
                                }
                            }
                        }

                        else if (i == 9) {
                            if (j == 0) {
                                //inspect above
                                if (floor[i-1][j].isInfected) {
                                    if (!floor[i-1][j].visited) {
                                        iCount++;
                                    }
                                    floor[i-1][j].visited = true;
                                    i--;
                                    foundInfected = true;
                                }
                                //inspect right
                                else if (floor[i][j+1].isInfected) {
                                    if (!floor[i][j+1].visited) {
                                        iCount++;
                                    }
                                    floor[i][j+1].visited = true;
                                    j++;
                                    foundInfected = true;
                                }
                            }
                            else if (j == 8) {
                                //inspect above
                                if (floor[i-1][j].isInfected) {
                                    if (!floor[i-1][j].visited) {
                                        iCount++;
                                    }
                                    floor[i-1][j].visited = true;
                                    i--;
                                    foundInfected = true;
                                }
                                //inspect left
                                else if (floor[i][j-1].isInfected) {
                                    if (!floor[i][j-1].visited) {
                                        iCount++;
                                    }
                                    floor[i][j-1].visited = true;
                                    j--;
                                    foundInfected = true;
                                }
                            }
                            else {
                                //inspect right
                                if (floor[i][j+1].isInfected) {
                                    if (!floor[i][j+1].visited) {
                                        iCount++;
                                    }
                                    floor[i][j+1].visited = true;
                                    j++;
                                    foundInfected = true;
                                }
                                //inspect left
                                else if (floor[i][j-1].isInfected) {
                                    if (!floor[i][j-1].visited) {
                                        iCount++;
                                    }
                                    floor[i][j-1].visited = true;
                                    j--;
                                    foundInfected = true;
                                }
                                //inspect above
                                else if (floor[i-1][j].isInfected) {
                                    if (!floor[i-1][j].visited) {
                                        iCount++;
                                    }
                                    floor[i-1][j].visited = true;
                                    i--;
                                    foundInfected = true;
                                }
                            }
                        }
                        else {
                            //inspect below
                            if (floor[i+1][j].isInfected) {
                                if (!floor[i+1][j].visited) {
                                    iCount++;
                                }
                                floor[i+1][j].visited = true;
                                i++;
                                foundInfected = true;
                            }
                            //inspect above
                            else if (floor[i-1][j].isInfected) {
                                if (!floor[i-1][j].visited) {
                                    iCount++;
                                }
                                floor[i-1][j].visited = true;
                                i--;
                                foundInfected = true;
                            }
                            //inspect right
                            else if (floor[i][j+1].isInfected) {
                                if (!floor[i][j+1].visited) {
                                    iCount++;
                                }
                                floor[i][j+1].visited = true;
                                j++;
                                foundInfected = true;
                            }
                            //inspect left
                            else if (floor[i][j-1].isInfected) {
                                if (!floor[i][j-1].visited) {
                                    iCount++;
                                }
                                floor[i][j-1].visited = true;
                                j--;
                                foundInfected = true;
                            }
                        }
                    }
                }
                else {
                    floor[i][j].visited = true;
                }
            }
        }
        return false;
    }
}
