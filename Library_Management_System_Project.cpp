#include <bits/stdc++.h>
using namespace std;

class Library
{
    private:
        struct Node
        {
            int book_id;
            int book_price;
            string book_name;
            string book_author;
            string book_publisher;
            Node *link;
        };
    
    public:
        Node *head = NULL;
        void menu();
        void insert_book();
        void search_book();
        void update_book();
        void delete_book();
        void display_book();
};

void Library::menu()
{
    to_return:

    system("cls"); // to clear the screen
    int option;
    cout << "\n\n\t\t\t=====Library Management System======";
    cout << "===============================================================\n\n\n";
    cout << "\n\n 1.insert\t\t2.search\t\t3.update\t\t4.delete\t\t5.display\t\t6.exit\n";
    cout << "enter your option: ";
    cin >> option;

    switch (option)
    {
        case 1:
            insert_book();
            break;
    
        case 2:
            search_book();
            break;
    
        case 3:
            update_book();
            break;
    
        case 4:
            delete_book();
            break;
    
        case 5:
            // sort_books();
            display_book();
            break;
    
        case 6:
            exit(0);
    
        default:
            cout << "\n\nenter correct option\n\n";
    }

    getchar();
    goto to_return;
}

void Library::insert_book()
{
    system("cls");
    cout << "\n\n\t\t\t=====Library Management System======";
    cout << "===============================================================\n\n\n";

    Node *new_node = new Node;
    cout << "enter book id : ";
    cin >> new_node->book_id;
    cout << "enter book price : ";
    cin >> new_node->book_price;
    cout << "enter book name : ";
    cin >> new_node->book_name;
    cout << "enter book author : ";
    cin >> new_node->book_author;
    cout << "enter book publisher : ";
    cin >> new_node->book_publisher;
    new_node->link = NULL;

    if (head == NULL)
    {
        head = new_node; // if till now no books are added.
    }

    else
    {
        Node *ptr = head;
        while (ptr->link != NULL)
            ptr = ptr->link;

        ptr->link = new_node;
    }

    cout << "\nnew book insertion is done\n";
}

void Library::search_book()
{
    system("cls");
    int temp_id;
    int fg = 0;

    cout << "\n\n\t\t\t=====Library Management System======";
    cout << "===============================================================\n\n\n";

    if (head == NULL)
    {
        cout << "\nno books are available in library to seach\n";
    }
    else
    {
        cout << "\nenter book id to search : ";
        cin >> temp_id;

        Node *ptr = head;
        while (ptr != NULL)
        {
            if (temp_id == ptr->book_id)
            {
                system("cls");
                cout << "\n\n\t\t\t=====Library Management System======";
                cout << "===============================================================\n\n\n";

                cout<<" Book is present and the details are : "<<endl;
                cout << "book id is :" << ptr->book_id << endl;
                cout << "book price is :" << ptr->book_price << endl;
                cout << "book price is :" << ptr->book_name << endl;
                cout << "book price is :" << ptr->book_author << endl;
                cout << "book price is :" << ptr->book_publisher << endl;
                fg++;
                break;
            }
            else ptr = ptr->link;
        }
        if (fg == 0)
            cout << "\nbook is not present\n";
    }
}

void Library::update_book()
{
    system("cls");
    int temp, fg = 0;

    cout << "\n\n\t\t\t=====Library Management System======";
    cout << "===============================================================\n\n\n";

    if (head == NULL)
    {
        cout << "\nno books are available in library to update\n";
    }

    else
    {
        cout << "enter for which  book id you need to change : ";
        cin >> temp;
        Node *ptr = head;

        while (ptr != NULL)
        {
            if (temp == ptr->book_id)
            {
                system("cls");
                cout << "\n\n\t\t\t=====Library Management System======";
                cout << "===============================================================\n\n\n";

                cout<<" enter new book id details to update : "<<endl;
                cout << "enter book new id : ";
                cin >> ptr->book_id;
                cout << "enter book price : ";
                cin >> ptr->book_price;
                cout << "enter book name : ";
                cin >> ptr->book_name;
                cout << "enter book author : ";
                cin >> ptr->book_author;
                cout << "enter book publisher : ";
                cin >> ptr->book_publisher;

                fg++;
                cout << "\nupdate book details is done\n";
                break;
            }
            ptr = ptr->link;
        }
        if (fg == 0)
            cout << "\ninvalid id entered\n";
    }
}

void Library::delete_book()
{
    system("cls");
    int temp, fg = 0;

    cout << "\n\n\t\t\t=====Library Management System======";
    cout << "===============================================================\n\n\n";

    if (head == NULL)
    {
        cout << "\nlibrary is empty\n";
    }

    else
    {
        cout << "\nenter book id : ";
        cin >> temp;

        if (temp == head->book_id)
        {
            Node *ptr = head;
            head = head->link;
            delete ptr;
            cout << "deletion is done\n";
            fg++;
        }
        else
        {
            Node *prev = head;
            Node *ptr = head;
            while (ptr != NULL)
            {
                if (temp == ptr->book_id)
                {
                    prev->link = ptr->link;
                    delete ptr;
                    cout << "\ndeletion is done\n";
                    fg++;
                    break;
                }
                prev = ptr;
                ptr = ptr->link;
            }
        }
        if (fg == 0)
            cout << "\ninvalid\n";
    }
}

void Library::display_book()
{
    system("cls");
    cout << "\n\n\t\t\t=====Library Management System======";
    cout << "===============================================================\n\n\n";
    if (head == NULL)
    {
        cout << "books are not available to display\n";
    }
    else
    {
        Node *ptr = head;
        while (ptr != NULL)
        {
            cout<<"[ ";
            cout << " [book id is : " << ptr->book_id << "] ";
            cout << " [book price is : " << ptr->book_price << "] ";
            cout << " [book name is : " << ptr->book_name << "] ";
            cout << " [book author is : " << ptr->book_author << "] ";
            cout << " [book publisher is : " << ptr->book_publisher << "] ";
            cout<<" ]"<<endl;
            ptr = ptr->link;
        }
    }
}
int32_t main()
{
    Library lib;
    lib.menu();
}
