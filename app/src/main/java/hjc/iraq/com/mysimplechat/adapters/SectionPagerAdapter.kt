package hjc.iraq.com.mysimplechat.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import hjc.iraq.com.mysimplechat.fragments.UsersFragment

class SectionPagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return UsersFragment()
//            1 -> return ChatsFragment()
        }
        return UsersFragment()
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "USERS"
//            1 -> "CHATS"
            else -> "UnKnow"
        }

    }
}