package com.example.android.navigation

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.android.navigation.databinding.FragmentTitleBinding

/**
 * A simple [Fragment] subclass.
 */
class TitleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //should return a layout
        //binding object

        val binding : FragmentTitleBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_title,
        container,false)

        //to tell  Android that title fragment has a menu
        setHasOptionsMenu(true)

     binding.playButton.setOnClickListener{
        //view: View -> view.findNavController().navigate(R.id.action_titleFragment_to_gameFragment)
         view:View -> view.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment()

     )
        }

        //binding.playButton.setOnClickListener(
                //Navigation.createNavigateOnClickListener(R.id.action_titleFragment_to_gameFragment)

       // )

        //return the root of the layout you inflated above

        return binding.root
        }

    //functionality for creating an option menu on title fragment

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        //we need to provide our menu to this function
        //it will inflate our menu structure
        inflater?.inflate(R.menu.overflow_menu, menu)

    }

    //now we need to connect the menu items to the navigation
    //i.e when an option is selected in the menu structure it should navigate the destination page
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
       // return super.onOptionsItemSelected(item)
       /*onNavDestinationSelected:
        this function attempts to navigate to the navdestination associated with the given menu item
        It will assume the menuitem id which is a valid action id or destination id to be navigated to
        Syntax:
        onNavDestinationSelected(MenuItem item, NavController navController)
        */

        /*
        !! operator is a null safety operation
        it is used to convert unsafe nullable type to non nullable type
        It throws an exception if the value is null
        */
       return NavigationUI.onNavDestinationSelected(item!!, view!!.findNavController()) || super.onOptionsItemSelected(item)
    }
}

