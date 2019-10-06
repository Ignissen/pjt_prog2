//#include <iostream>

#include <CodeFromUML/Mage.h>
#include <CodeFromUML/Warrior.h>


using std::cout;
using std::cin;
using std::endl;


int main()
{
	int type;
	cout << "Milyen kaszttal szeretnél játszani?\n(1)Harcos\n(2)Mágus" << endl;
	cin >> type;
	if(cin.fail())
	{
		std::cerr << "Nem megfelelő paraméter." <<endl;
		return -1;
	}
	if(type == 1)
	{
		CodeFromUML::Warrior* w = new CodeFromUML::Warrior();
		int choice;
		while(w->isAlive())
		{
			cout << "Elloptál egy tyúkot, ezért vérdíj van a fejeden.\nSzembejön veled egy katona, mit teszel?\n(1)Elfutsz\n(2)Harcolsz\n(3)Gyógyítasz magadon" << endl;
			cin >> choice;
			if(choice == 1)
			{
				cout << "Menekülés közben megbotlottál egy tücsökben, a katona a fejedet vette." << endl;
				break;
			}
			else if(choice == 2)
			{
				w->attack();
			}
			else
			{
				w->heal();
			}
		}

	}
	else
	{
		CodeFromUML::Mage* m = new CodeFromUML::Mage();
		int choice;
		while(m->isAlive())
		{
			cout << "Elloptál egy tyúkot, ezért vérdíj van a fejeden.\nSzembejön veled egy katona, mit teszel?\n(1)Elfutsz\n(2)Harcolsz\n(3)Gyógyítasz magadon" << endl;
			cin >> choice;
			if(choice == 1)
			{
				cout << "Menekülés közben megbotlottál egy tücsökben, a katona a fejedet vette." << endl;
				break;
			}
			else if(choice == 2)
			{
				m->attack();
			}
			else
			{
				m->heal();
			}
		}
	}
	cout << "GAME OVER" <<endl;
	return 0;
}
