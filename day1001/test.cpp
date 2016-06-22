#include <stdio.h>
#include <iostream>
#include <memory>
#include <map>
#include <cstring>
#include <vector>
#include <climits>
using namespace std;

int addThem( int& x, int y){
	x =x + 1;
	return x+y;
}



//class ExDer : public ExDer1 {};
//
//

const int Mon =1;


template <typename T> class Base {
    public:
        T t;
}; 
template <typename T, class Q> class Derived : public Base<T> { 
    private:
        Q* q;
};


class Bad
{
    public:
        ~Bad()
        {
            throw 1;
        }
};



class Column {
    private:
        string m_Name;
    public:
        // Overrides
        string Name(){
            return m_Name;
        }

        // Ctors
        Column(string NewName){
            m_Name = NewName;
        }

        // Dtors
        ~Column(){
            cout << "destructor! \n";
        }
};

// Typedefs
    typedef std::map<int, Column> ColumnContainer;
    ColumnContainer m_Container;

class SomeClass
{
    public:
        int data;
    protected:
        class Nest
        {
            public:
                int nested;
        };
    public:
        static Nest* createNest(){return new Nest;}
};

class Derived1 : public SomeClass
{
    public:
        void use_someclass();
};
 
void Derived1::use_someclass()
{
    Nest* nst = createNest();
    nst->nested = 5;
}

class AA{
    public:
        int a;
        ~AA()   {   cout<<"destructor AA";}
};

class XX{
    public:
        int pubx;
        ~XX()   {   
            myVirtual();           
            cout<<"destructor XX";
        }
        virtual void myVirtual(){
            cout<<"base class virtual"<<endl;
        }
    protected:
        int protx;
    private:
        int privx;
};

class YY : public XX{
    public:
        virtual void myVirtual(){
            cout<<"derived class virtual"<<endl;
        }
        int y;
        ~YY()   {   cout<<"destructor YY";}
        void test(){
            pubx = 10;
            protx = 19;
        }
};

class ZZ : public YY{
    public:
        void test(){
            //pubx = 10;
            //protx = 19;
        }
};


class Object { public:     
    Object() {}          
    void Print() const     {         cout << "const" << endl;     }  
    void Print()     {         cout << "mutable" << endl;     } 
};  
void print_obj(const Object& obj) {     obj.Print(); }  

class TestPrint { 
    public: 
        int i;
        TestPrint() : i(8) { }
        void Print()     {         std::cout << "TestPrint" << std::endl;     }  
        void Print() const     {         std::cout << "const TestPrint" << std::endl;     }  
        void Print() volatile     {         std::cout << "volatile TestPrint" << std::endl;     }  
        void Print() const volatile     {         std::cout << "const volatile TestPrint" << std::endl;     } 
};   

int solution(vector<int> &A) {
    // write your code in C++11 (g++ 4.8.2)
    int N = A.size();
    vector<long long> forward(N);
    vector<long long> backward(N);

    long long sum = 0;
    for(int i =0; i<N; i++){
        forward[i] = sum;
        sum += A[i];
    }
    //forward[N] = sum;

    sum = 0;
    for(int i =N-1; i>=0; i--){
        backward[i] = sum;   
        sum += A[i];
    }
    for(int i =0; i<N; i++){
        if(forward[i] == backward[i]) return i;

    }
    return -1;
}

class ExBase 
{ 
    private: 
		static int stat ; 
	public: 
		static void setStat(int n){ stat = n; }
		static int GetStat(){ return stat; } 
        int multipleInherite() { cout<<"multiInherite"<<endl ;}
};

int ExBase::stat = 25;

class ExDer1 : public ExBase 
{ 
	public: 
		friend int Der1Fn(){ return ExBase::GetStat();} 
		//friend int Der1Fn();
};

int Der1Fn();

class ExDer2 : public ExBase
{}; 

class ExDer : public ExDer1, public ExDer2
{}; 

int main()
{
    enum DOW{ Mon = 11, Tue = 12 }; 
    int var = Mon;
    DOW dow = Tue;
    cout<<var<<"----"<<dow<<endl;
    cout<<"OK"<<endl;
    return 0;
}


/*
int main(){
    Column *c = new Column("Test");
    cout << "CREATED: " << c->Name() << "\n";
    pair<int, Column> pair_Column = make_pair(0, *c);
    bool sucess = m_Container.insert(pair_Column).second;
    //map<int,Column>::iterator  it = m_Container->insert(std::make_pair(0, *c)).first;
    if(sucess)  cout<< "its a sucess!";
    //cout << "AGAIN: " << c->Name() << "\n";
    //cout << "first int is   "<<it;
    enum DOW{ Mon = 11, Tue = 12 }; 
    int var = Mon;
    DOW dow = Tue;
    cout<<var<<"----"<<dow<<endl;
	return 0;
}
*/










